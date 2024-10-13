package com.safarr.app.utils;

import com.safarr.app.entity.AppUser;
import com.safarr.app.model.RegisterUserRequest;

public class Utils {

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static AppUser userRequestToAppUserEntity(RegisterUserRequest registerUserRequest) {
        return AppUser.builder()
                .username(registerUserRequest.getUsername())
                .email(registerUserRequest.getEmail())
                .password(registerUserRequest.getPassword())
                .firstName(registerUserRequest.getFirstName())
                .lastName(registerUserRequest.getLastName())
                .build();
    }
}
