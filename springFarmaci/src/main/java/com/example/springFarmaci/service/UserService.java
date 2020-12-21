package com.example.springFarmaci.service;

import com.example.springFarmaci.repository.UserRepository;
import com.example.springFarmaci.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(String email) {
        return userRepository.findByEmail(email);
    }
}
