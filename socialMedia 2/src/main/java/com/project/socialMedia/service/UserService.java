package com.project.socialMedia.service;

import com.project.socialMedia.entity.User;
import com.project.socialMedia.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.socialMedia.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream().map(user->{
                    UserResponse userResponse = new UserResponse();
                    userResponse.setUserId(user.getId());
                    userResponse.setName(user.getName());
                    userResponse.setEmail(user.getEmail());
                    return userResponse;
                }).toList();
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void createUser(String email, String name, String password) {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        userRepository.save(user);
    }
}
