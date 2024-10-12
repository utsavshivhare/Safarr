package com.safarr.app.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    public String login(String userNameOrEmail, String password) {
        // Perform authentication
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userNameOrEmail, password));

        // Retrieve user details
        UserDetails userDetails = userDetailsService.loadUserByUsername(userNameOrEmail);

        // Generate JWT Token
        return jwtUtil.generateToken(userDetails.getUsername());
    }
}
