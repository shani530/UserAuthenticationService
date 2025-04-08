package com.example.userauthenticationservice.service;


import com.example.userauthenticationservice.model.User;

public interface UserService {
    // add a method to save the user
    public String saveUser(User user);
    // add a method to get the current user
    public String getCurrentUser();
    // add a method to signout the user
    public String signout();

}
