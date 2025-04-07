package com.example.demo;

public class User {
    private String username;
    private String password; // Encrypted

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters only (no setters needed)
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
