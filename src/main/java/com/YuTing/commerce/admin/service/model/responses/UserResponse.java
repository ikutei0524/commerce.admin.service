package com.YuTing.commerce.admin.service.model.responses;

import com.YuTing.commerce.admin.service.model.Order;
import com.YuTing.commerce.admin.service.model.Review;
import com.YuTing.commerce.admin.service.model.User;
import com.YuTing.commerce.admin.service.model.UserSegment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private UserSegment userSegments;

    private Order orders;

    private Review reviews;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate birthday;

    private String address;

    private String city;

    private String state;

    private String zipcode;

    private String password;

    private boolean hasNewsletter;

    private LocalDateTime lastSeenAt;

    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;

    private boolean isDelete;

    private String role;

    public UserResponse(User users) {
    }
}
