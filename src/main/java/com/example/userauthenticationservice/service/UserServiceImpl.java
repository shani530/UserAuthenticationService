package com.example.userauthenticationservice.service;

import com.example.userauthenticationservice.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    // add a method to save the user


    @Override
    public String saveUser(User user) {
        return "User saved";
    }

    // add a method to get the current user
    public String getCurrentUser() {
        return "Current user";
    }
    // add a method to signout the user
    public String signout() {
        return "User signed out";
    }
}
