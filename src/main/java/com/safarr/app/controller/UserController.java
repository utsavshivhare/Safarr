package com.safarr.app.controller;

import static com.safarr.app.utils.Utils.userRequestToAppUserEntity;

import com.safarr.app.Config.AuthenticationService;
import com.safarr.app.exceptionhandler.AppException;
import com.safarr.app.model.LoginRequest;
import com.safarr.app.model.LoginResponse;
import com.safarr.app.model.RegisterResponse;
import com.safarr.app.model.RegisterUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.safarr.app.service.AppUserServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private AppUserServiceImpl appUserService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterUserRequest registerUserRequest) {
        String registerUserResponse = appUserService.registerUser(userRequestToAppUserEntity(registerUserRequest));
        return ResponseEntity.ok(
                RegisterResponse.builder()
                        .message(registerUserResponse)
                        .success(true)
                        .build()
        );
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            String token = authenticationService.login(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(LoginResponse
                    .builder().success(true).message("Login successful").token(token).build());
        } catch (Exception e) {
            throw new AppException("Login failed: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}