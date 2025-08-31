package com.YuTing.commerce.admin.service.controllers;


import com.YuTing.commerce.admin.service.model.User;
import com.YuTing.commerce.admin.service.model.requests.UpdateUserRequest;
import com.YuTing.commerce.admin.service.repositories.UserRepository;
import com.YuTing.commerce.admin.service.model.responses.UserResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    @Autowired

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<User>users = userRepository.findAll();
        return ResponseEntity.ok(users.stream().map(UserResponse::new).toList());
    }




    @GetMapping("/{id}")
    public ResponseEntity<UserResponse>getProductById(@PathVariable int id ){
        Optional<User> user = userRepository.findById();
        if(user.isPresent()){
            UserResponse response = new UserResponse(user.get());
            return ResponseEntity.ok(response);
    }else {
            return  ResponseEntity.notFound().build();
        }









    @PutMapping("/{id}")
    public ResponseEntity<UserResponse>updateUsersById(@PathVariable int id,@RequestBody UpdateUserRequest request){
            Optional<User> user = userRepository.findById();

        }





    @PostMapping





    @DeleteMapping("/{id}")








    }
}
