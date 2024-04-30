package com.project.socialMedia.service;

import com.project.socialMedia.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    public String login(String email, String password) {
        Optional<User> userOptional = userService.getUserByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(password)) {
                return "Login Successful";
            } else {
                return "Username/Password Incorrect";
            }
        } else {
            return "User does not exist";
        }
    }

    public String signup(String email, String name, String password) {
        Optional<User> existingUser = userService.getUserByEmail(email);
        if (existingUser.isPresent()) {
            return "Forbidden, Account already exists";
        } else {
            userService.createUser(email, name, password);
            return "Account Creation Successful";
        }
    }
}

