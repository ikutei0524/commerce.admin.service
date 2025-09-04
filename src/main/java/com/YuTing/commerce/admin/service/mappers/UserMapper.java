package com.YuTing.commerce.admin.service.mappers;

import com.YuTing.commerce.admin.service.dtos.requests.UserRequest;
import com.YuTing.commerce.admin.service.dtos.responses.UserResponse;
import com.YuTing.commerce.admin.service.dtos.responses.UserSegmentResponse;
import com.YuTing.commerce.admin.service.model.User;
import com.YuTing.commerce.admin.service.model.UserSegment;

import java.util.stream.Collectors;

public class UserMapper {

    public UserResponse toUserResponse(User user) {
        if (user == null) return null;

        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setBirthday(user.getBirthday());
        dto.setAddress(user.getAddress());
        dto.setCity(user.getCity());
        dto.setState(user.getState());
        dto.setZipcode(user.getZipcode());
        dto.setRole(user.getRole());
        dto.setHasNewsletter(user.isHasNewsletter());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setLastSeenAt(user.getLastSeenAt());

        if (user.getUserSegments() != null) {
            dto.setUserSegments(
                    user.getUserSegments().stream()
                            .map(this::toUserSegmentResponse)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    public User toUser(UserRequest dto) {
        if (dto == null) return null;

        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setBirthday(dto.getBirthday());
        user.setAddress(dto.getAddress());
        user.setCity(dto.getCity());
        user.setState(dto.getState());
        user.setZipcode(dto.getZipcode());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setHasNewsletter(dto.isHasNewsletter());
        return user;
    }

    private UserSegmentResponse toUserSegmentResponse(UserSegment userSegment) {
        if (userSegment == null) return null;

        UserSegmentResponse dto = new UserSegmentResponse();
        dto.setId(userSegment.getId());
        if (userSegment.getSegment() != null) {
            dto.setSegmentName(userSegment.getSegment().getName());
        }
        dto.setCreatedAt(userSegment.getCreatedAt());
        return dto;
    }
}

