package com.safarr.app.controller;

import com.safarr.app.Config.AuthenticationService;
import com.safarr.app.model.*;
import com.safarr.app.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("Safarr me swagat h");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AppUser user) {
        try {
            String token = authenticationService.login(user.getUsername(), user.getPassword());
            return ResponseEntity.ok("Login successful. JWT Token: " + token);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Login failed: " + e.getMessage());
        }
    }
}