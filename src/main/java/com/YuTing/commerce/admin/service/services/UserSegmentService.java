package com.YuTing.commerce.admin.service.services;

import com.YuTing.commerce.admin.service.model.Segment;
import com.YuTing.commerce.admin.service.model.User;
import com.YuTing.commerce.admin.service.model.UserSegment;
import com.YuTing.commerce.admin.service.repositories.SegmentRepository;
import com.YuTing.commerce.admin.service.repositories.UserRepository;
import com.YuTing.commerce.admin.service.repositories.UserSegmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSegmentService {

    private final UserSegmentRepository userSegmentRepository;
    private final UserRepository userRepository;
    private final SegmentRepository segmentRepository;

    /**
     * 把某個 user 加入某個 segment
     */
    public UserSegment addUserToSegment(Integer userId, Integer segmentId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Segment segment = segmentRepository.findById(segmentId)
                .orElseThrow(() -> new RuntimeException("Segment not found"));

        // 檢查是否已存在
        boolean exists = userSegmentRepository.findByUserId(userId).stream()
                .anyMatch(us -> us.getSegment().getId().equals(segmentId));

        if (exists) {
            throw new RuntimeException("User already in this segment");
        }

        UserSegment userSegment = new UserSegment();
        userSegment.setUser(user);
        userSegment.setSegment(segment);
        userSegment.setCreatedAt(LocalDateTime.now());

        return userSegmentRepository.save(userSegment);
    }

    /**
     * 把某個 user 從某個 segment 移除
     */
    public void removeUserFromSegment(Integer userId, Integer segmentId) {
        List<UserSegment> list = userSegmentRepository.findByUserId(userId);
        list.stream()
                .filter(us -> us.getSegment().getId().equals(segmentId))
                .forEach(userSegmentRepository::delete);
    }

    /**
     * 查詢某個使用者屬於哪些 segment
     */
    public List<UserSegment> getSegmentsByUser(Integer userId) {
        return userSegmentRepository.findByUserId(userId);
    }

    /**
     * 查詢某個 segment 下有哪些使用者
     */
    public List<UserSegment> getUsersBySegment(Integer segmentId) {
        return userSegmentRepository.findBySegmentId(segmentId);
    }
}
