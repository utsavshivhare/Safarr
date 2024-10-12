package com.safarr.app.service;

import com.safarr.app.entity.AppUser;
import com.safarr.app.exceptionhandler.AppException;
import com.safarr.app.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String registerUser(AppUser user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()
                || user.getEmail() == null || user.getEmail().isEmpty()
                || user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new AppException("All fields are required", HttpStatus.BAD_REQUEST);
        }
        if (appUserRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new AppException("Email is already in use", HttpStatus.BAD_REQUEST);
        }
        if (appUserRepository.findByUsername(user.getUsername()) != null) {
            throw new AppException("Username is already in use", HttpStatus.BAD_REQUEST);

        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        appUserRepository.save(user);
        return "Safarr me swagat h";
    }
}