package com.YuTing.commerce.admin.service.repositories;

import com.YuTing.commerce.admin.service.model.Segment;
import com.YuTing.commerce.admin.service.model.UserSegment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSegmentRepository extends JpaRepository<UserSegment, Integer> {
    List<UserSegment> findByUserId(Integer userId);
    List<UserSegment> findBySegmentId(Integer segmentId);

}