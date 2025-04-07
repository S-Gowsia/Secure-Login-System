package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.*;

@Configuration
public class SecurityConfig {

    private final Map<String, User> users = new HashMap<>();

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        return new InMemoryUserDetailsManager() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                User user = users.get(username);
                if (user == null) throw new UsernameNotFoundException(username);
                return User.withUsername(user.getUsername())
                        .password(user.getPassword())
                        .roles("USER")
                        .build();
            }

            public void register(User user) {
                users.put(user.getUsername(), user);
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/register").permitAll()
            .anyRequest().authenticated()
            .and().httpBasic();
        return http.build();
    }

    public Map<String, User> getUsers() {
        return users;
    }
}
