package com.example.userauthenticationservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController
{
    // add signup , signout and signin methods here
    @PostMapping("/signup")
    public String signup()
    {
        return "signup";
    }
    // add a method to get the current user
    @GetMapping("/currentuser")
    public String getCurrentUser()
    {
        return "current user";
    }
    // add a method to signout the user
    @PostMapping("/signout")
    public String signout()
    {
        return "signout";
    }

}
