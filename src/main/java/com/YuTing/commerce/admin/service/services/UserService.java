package com.YuTing.commerce.admin.service.services;

import com.YuTing.commerce.admin.service.dtos.requests.UserRequest;
import com.YuTing.commerce.admin.service.dtos.responses.PageResponse;
import com.YuTing.commerce.admin.service.dtos.responses.UserResponse;
import com.YuTing.commerce.admin.service.mappers.UserMapper;
import com.YuTing.commerce.admin.service.model.Segment;
import com.YuTing.commerce.admin.service.model.User;
import com.YuTing.commerce.admin.service.model.UserSegment;
import com.YuTing.commerce.admin.service.repositories.SegmentRepository;
import com.YuTing.commerce.admin.service.repositories.UserRepository;
import com.YuTing.commerce.admin.service.repositories.UserSegmentRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserSegmentRepository userSegmentRepository;
    private final UserRepository userRepository;
    private final SegmentRepository segmentRepository;
    private final UserMapper userMapper = new UserMapper();


    public UserResponse createUser(UserRequest request) {
        User user = userMapper.toUser(request);
        user.setCreatedAt(LocalDateTime.now());
        user.setLastSeenAt(LocalDateTime.now());
        user.setDelete(false);
        User savedUser = userRepository.save(user);

        // 如果有 segmentIds，就建立 UserSegment 關聯
        if (request.getSegmentIds() != null) {
            for (Integer segmentId : request.getSegmentIds()) {
                Segment segment = segmentRepository.findById(segmentId)
                        .orElseThrow(() -> new RuntimeException("若沒有找到Segment:" + segmentId));

                UserSegment userSegment = new UserSegment();
                userSegment.setUser(user);
                userSegment.setSegment(segment);
                userSegment.setCreatedAt(LocalDateTime.now());

                userSegmentRepository.save(userSegment);
            }
        }

        // 3. 回傳 Response
        return userMapper.toUserResponse(userRepository.findById(user.getId()).orElseThrow());
    }






    public UserResponse updateUser(int id, UserRequest request) {
        User user = userRepository.findById(id).orElseThrow();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setBirthday(request.getBirthday());
        user.setAddress(request.getAddress());
        user.setCity(request.getCity());
        user.setState(request.getState());
        user.setZipcode(request.getZipcode());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setHasNewsletter(request.isHasNewsletter());
        user.setLastSeenAt(LocalDateTime.now());

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(int id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setDelete(true);
        user.setDeletedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    public UserResponse getUser(int id) {
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow());
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .filter(user -> !user.isDelete())
                .map(userMapper::toUserResponse)
                .toList();
    }

    //page api
    public PageResponse<UserResponse> getUsersWithPagination(
            int page, int size, String query, Boolean hasNewsletter, Integer segmentId) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Specification<User> spec = userSpecification(query, hasNewsletter, segmentId);

        Page<UserResponse> usersPage = userRepository.findAll(spec, pageRequest)
                .map(userMapper::toUserResponse);

        return new PageResponse<>(
                usersPage.getContent(),
                usersPage.getNumber(),
                usersPage.getSize(),
                usersPage.getTotalElements(),
                usersPage.getTotalPages()
        );
    }



    private Specification<User> userSpecification(String queryName, Boolean hasNewsletter, Integer segmentId) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 模糊搜尋 firstName / lastName
            if (queryName != null && !queryName.isEmpty()) {
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + queryName.toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + queryName.toLowerCase() + "%")
                ));
            }

            // 篩選 newsletter
            if (hasNewsletter != null) {
                predicates.add(criteriaBuilder.equal(root.get("hasNewsletter"), hasNewsletter));
            }

            // 篩選 segment
            if (segmentId != null) {
                Join<User, UserSegment> userUserSegmentJoin = root.join("userSegments");
                predicates.add(criteriaBuilder.equal(userUserSegmentJoin.get("segment").get("id"), segmentId));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }



}

