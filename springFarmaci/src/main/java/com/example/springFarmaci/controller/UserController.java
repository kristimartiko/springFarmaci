package com.example.springFarmaci.controller;

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
    private JwtUtil jwtUtil;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @GetMapping("/users")
        public List<User> getAllUsers() {
            return userService.findAll();
        }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthentiactionRequest authentiactionRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authentiactionRequest.getUsername(), authentiactionRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password");
        }
        final UserDetails userDetails = myUserDetailService.loadUserByUsername(authentiactionRequest.getUsername());

        final String firstName = myUserDetailService.getCurrentUsername(authentiactionRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("/user{email}")
    public User getUser(@PathVariable String email) {
        return userService.findOne(email);
    }

}
