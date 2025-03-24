package com.example.userauthenticationservice.service;


public interface UserService {
    // add a method to save the user
    public String saveUser();
    // add a method to get the current user
    public String getCurrentUser();
    // add a method to signout the user
    public String signout();

}
