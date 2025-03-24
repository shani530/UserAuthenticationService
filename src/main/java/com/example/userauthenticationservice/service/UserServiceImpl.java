package com.example.userauthenticationservice.service;

public class UserServiceImpl implements UserService {
    // add a method to save the user
    public String saveUser() {
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
