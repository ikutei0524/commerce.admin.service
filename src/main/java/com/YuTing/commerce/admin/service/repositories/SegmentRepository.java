package com.YuTing.commerce.admin.service.repositories;

import com.YuTing.commerce.admin.service.model.Segment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SegmentRepository extends JpaRepository<Segment, Integer> {

    // 查所有未刪除的 Segment
    List<Segment> findByDeletedAtIsNull();

    // 查特定 ID 的未刪除 Segment
    Optional<Segment> findByIdAndDeletedAtIsNull(Integer id);
}
