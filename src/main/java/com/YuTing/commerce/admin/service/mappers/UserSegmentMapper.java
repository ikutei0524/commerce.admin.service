package com.YuTing.commerce.admin.service.mappers;

import com.YuTing.commerce.admin.service.dtos.responses.UserSegmentResponse;
import com.YuTing.commerce.admin.service.model.UserSegment;

public class UserSegmentMapper {

    public static UserSegmentResponse toResponse(UserSegment us) {
        String fullName = null;
        if (us.getUser() != null) {
            fullName = (us.getUser().getFirstName() != null ? us.getUser().getFirstName() : "") +
                    " " +
                    (us.getUser().getLastName() != null ? us.getUser().getLastName() : "");
        }

        return new UserSegmentResponse(
                us.getId(),
                us.getCreatedAt(),
                us.getDeletedAt(),
                us.getUser() != null ? us.getUser().getId() : null,
                fullName,  // 使用姓名取代 username
                us.getSegment() != null ? us.getSegment().getId() : null,
                us.getSegment() != null ? us.getSegment().getName() : null
        );
    }
}
