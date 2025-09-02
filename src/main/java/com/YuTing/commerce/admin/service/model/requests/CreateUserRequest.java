package com.YuTing.commerce.admin.service.model.requests;

import com.YuTing.commerce.admin.service.model.Order;
import com.YuTing.commerce.admin.service.model.Review;

import com.YuTing.commerce.admin.service.model.UserSegment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate birthday;

    private String address;

    private String city;

    private String state;

    private String zipcode;

    private boolean hasNewsletter;






}
