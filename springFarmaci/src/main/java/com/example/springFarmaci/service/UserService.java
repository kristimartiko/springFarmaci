package com.example.springFarmaci.service;

import com.example.springFarmaci.dto.UserDTO;
import com.example.springFarmaci.models.Roles;
import com.example.springFarmaci.repository.RoleRepository;
import com.example.springFarmaci.repository.UserRepository;
import com.example.springFarmaci.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

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
        verifyRoles();
        user.setRoles(new HashSet<>(Arrays.asList(roleRepository.getOne(6L))));

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
        verifyRoles();
        if (userDTO.getRole().equals("User")){
            user.setRoles(new HashSet<>(Arrays.asList(roleRepository.getOne(6L))));

        } else {
            user.setRoles(new HashSet<>(Arrays.asList(roleRepository.getOne(2L))));
        }
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
        if (userDTO.getRole().equals("User")){
            user.setRoles(new HashSet<>(Arrays.asList(roleRepository.getOne(6L))));

        } else {
            user.setRoles(new HashSet<>(Arrays.asList(roleRepository.getOne(2L))));
        }
        userRepository.save(user);
    }

    private void verifyRoles() {
        if(!roleRepository.findById(6L).isPresent()) {
            roleRepository.save(new Roles("User"));
        }
        if(!roleRepository.findById(2L).isPresent()) {
            roleRepository.save(new Roles("Admin"));
        }
    }

    public Boolean isEmailPresent(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            return false;
        }
        return true;
    }
}
