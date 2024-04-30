package com.project.socialMedia.controller;

import com.project.socialMedia.entity.User;
import com.project.socialMedia.request.UserLoginRequest;
import com.project.socialMedia.request.UserSignupRequest;
import com.project.socialMedia.service.AuthService;
import com.project.socialMedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest request) {
        Optional<User> userOptional = userService.getUserByEmail(request.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(request.getPassword())) {
                return ResponseEntity.ok("Login Successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username/Password Incorrect");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignupRequest request) {
        Optional<User> existingUser = userService.getUserByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Forbidden, Account already exists");
        } else {
            userService.createUser(request.getEmail(), request.getName(), request.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body("Account Creation Successful");
        }
    }
}

