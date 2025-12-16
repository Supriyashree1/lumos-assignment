package com.lumos.assignment.service;

import com.lumos.assignment.model.UserEntity;
import com.lumos.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean isUsernameTaken(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity authenticate(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
