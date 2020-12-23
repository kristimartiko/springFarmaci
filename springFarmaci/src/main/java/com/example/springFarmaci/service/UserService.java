package com.example.springFarmaci.service;

import com.example.springFarmaci.dto.UserDTO;
import com.example.springFarmaci.repository.UserRepository;
import com.example.springFarmaci.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(String email) {
        return userRepository.findByEmail(email);
    }

    public User signUp(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        if(userRepository.findByEmail(userDTO.getEmail()) != null) {
        }
        return userRepository.save(user);
    }

    public User addUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setCreatedAt(new Date());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        user.setToDate(new Date());
        userRepository.save(user);
    }

    public void updateUser(UserDTO userDTO, Long id) {
        User user = userRepository.findById(id).orElse(null);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);
    }

    public Boolean isEmailPresent(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            return false;
        }
        return true;
    }
}
