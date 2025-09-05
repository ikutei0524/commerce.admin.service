package com.YuTing.commerce.admin.service.dtos.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {
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
    private String role;

    private List<Integer> segmentIds;
}
