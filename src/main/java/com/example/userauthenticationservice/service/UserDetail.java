package com.example.userauthenticationservice.service;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserDetail implements org.springframework.security.core.userdetails.UserDetails {
    private String username;
    private String password;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
