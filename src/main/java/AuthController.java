package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private SecurityConfig config;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        String encodedPass = encoder.encode(password);
        User user = new User(username, encodedPass);
        ((InMemoryUserDetailsManager) config.userDetailsService()).register(user);
        return "User registered successfully!";
    }
}
