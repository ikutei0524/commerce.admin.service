package com.YuTing.commerce.admin.service.model.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

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

}
