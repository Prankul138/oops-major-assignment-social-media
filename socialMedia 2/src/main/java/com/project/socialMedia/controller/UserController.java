package com.project.socialMedia.controller;

import com.project.socialMedia.entity.User;
import com.project.socialMedia.response.UserResponse;
import com.project.socialMedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserDetail(@RequestParam("userID") Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            UserResponse userResponse = new UserResponse();
            userResponse.setUserId(user.getId());
            userResponse.setName(user.getName());
            userResponse.setEmail(user.getEmail());
            return ResponseEntity.ok(userResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        }
    }
}

