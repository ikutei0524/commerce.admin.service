package com.YuTing.commerce.admin.service.services;


import com.YuTing.commerce.admin.service.dtos.requests.LoginRequest;
import com.YuTing.commerce.admin.service.model.User;
import com.YuTing.commerce.admin.service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtAuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return jwtService.generateToken(user);
    }

    public String login(LoginRequest request) {
        Optional<User>userOptional = userRepository.findByEmail(request.getEmail());
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(passwordEncoder.matches(request.getPassword(),user.getPassword())){
                return jwtService.generateToken(user);
            }
        }
        throw new RuntimeException("無效認證");
    }
}
