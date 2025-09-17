package com.YuTing.commerce.admin.service.controllers;


import com.YuTing.commerce.admin.service.dtos.requests.LoginRequest;
import com.YuTing.commerce.admin.service.dtos.requests.UserRequest;
import com.YuTing.commerce.admin.service.dtos.responses.AuthResponse;
import com.YuTing.commerce.admin.service.mappers.UserMapper;
import com.YuTing.commerce.admin.service.model.User;

import com.YuTing.commerce.admin.service.services.JwtAuthService;
import com.YuTing.commerce.admin.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/jwt")
public class JwtAuthController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtAuthService jwtAuthService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse>register(@RequestBody UserRequest userRequest) {
        User user = userMapper.toUser(userRequest);
        String token = jwtAuthService.register(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse>login(@RequestBody LoginRequest request){
        String token = jwtAuthService.login(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}


