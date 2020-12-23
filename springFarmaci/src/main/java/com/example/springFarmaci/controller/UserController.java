package com.example.springFarmaci.controller;

import com.example.springFarmaci.dto.UserDTO;
import com.example.springFarmaci.models.AuthentiactionRequest;
import com.example.springFarmaci.models.AuthenticationResponse;
import com.example.springFarmaci.models.User;
import com.example.springFarmaci.service.MyUserDetailService;
import com.example.springFarmaci.service.UserService;
import com.example.springFarmaci.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @GetMapping("/users")
    public List<User> getAllUsers() {
            return userService.findAll();
        }

    @PostMapping("users/signup")
    public User addUser(@RequestBody UserDTO userDTO) {
        return userService.signUp(userDTO);
    }

    @PostMapping("/users/add")
    public User addUserFormAdmin(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @PutMapping("/users/update/{id}")
    public void update(@RequestBody UserDTO userDTO,
                       @PathVariable Long id) {
        userService.updateUser(userDTO, id);
    }

    @DeleteMapping("users/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthentiactionRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = myUserDetailService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("/user{email}")
    public User getUser(@PathVariable String email) {
        return userService.findOne(email);
    }

}
