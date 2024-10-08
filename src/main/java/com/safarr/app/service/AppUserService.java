package com.safarr.app.service;

import com.safarr.app.model.AppUser;
import com.safarr.app.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<String> registerUser(AppUser user) {
        if(user.getUsername() == null || user.getUsername().isEmpty()
                || user.getEmail() == null || user.getEmail().isEmpty()
                || user.getPassword() == null || user.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("All fields are required");
        }
        if (appUserRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email is already in use");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        appUserRepository.save(user);
        return ResponseEntity.ok("Safarr me swagat h");
    }
}