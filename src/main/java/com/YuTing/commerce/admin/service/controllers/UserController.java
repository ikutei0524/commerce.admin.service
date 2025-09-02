package com.YuTing.commerce.admin.service.controllers;


import com.YuTing.commerce.admin.service.model.User;
import com.YuTing.commerce.admin.service.model.requests.CreateUserRequest;
import com.YuTing.commerce.admin.service.model.requests.UpdateUserRequest;
import com.YuTing.commerce.admin.service.repositories.UserRepository;
import com.YuTing.commerce.admin.service.model.responses.UserResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public ResponseEntity<UserResponse>getProductById(@PathVariable int id ) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            UserResponse response = new UserResponse(user.get());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }









    @PutMapping("/{id}")
    public ResponseEntity<UserResponse>updateUsersById(@PathVariable int id,@RequestBody UpdateUserRequest request){
            Optional<User> user = userRepository.findById(id);

            if(user.isPresent()){
                User updatedUser = user.get();
                System.out.println("更新前:" + updatedUser);
                updatedUser.setFirstName(request.getFirstName());
                updatedUser.setLastName(request.getLastName());
                updatedUser.setEmail(request.getEmail());
                updatedUser.setPassword(request.getPassword());
                updatedUser.setBirthday(request.getBirthday());
                updatedUser.setAddress(request.getAddress());
                updatedUser.setCity(request.getCity());
                updatedUser.setState(request.getState());
                updatedUser.setZipcode(request.getZipcode());
                updatedUser.setHasNewsletter(request.isHasNewsletter());
                System.out.println("儲存前:" + updatedUser);
                User savedUser = userRepository.save(updatedUser);
                UserResponse response = new UserResponse(savedUser);
                return  ResponseEntity.ok(response);
            }else {
                return ResponseEntity.notFound().build();
            }

        }





    @PostMapping
    public ResponseEntity<UserResponse>createUser(@RequestBody CreateUserRequest request){
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setBirthday(request.getBirthday());
        user.setAddress(request.getAddress());
        user.setCity(request.getCity());
        user.setState(request.getState());
        user.setZipcode(request.getZipcode());
        user.setHasNewsletter(request.isHasNewsletter());
        user.setCreatedAt(LocalDateTime.now());
        user.setLastSeenAt(LocalDateTime.now());
        user.setDelete(false);


        System.out.println("儲存之前"+user);
        User saveduser = userRepository.save(user);
        // 包裝成 UserResponse (對外不會回傳密碼)
        UserResponse response = new UserResponse(saveduser);
        return ResponseEntity.ok(response);
    }






    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // 軟刪除：標記為刪除，並設定刪除時間
            user.setDelete(true);
            user.setDeletedAt(LocalDateTime.now());

            userRepository.save(user);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }








    }

