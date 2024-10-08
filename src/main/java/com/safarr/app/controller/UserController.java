package com.safarr.app.controller;

import com.safarr.app.Config.AuthenticationService;
import com.safarr.app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.safarr.app.service.AppUserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AppUser user) {
        return appUserService.registerUser(user);
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