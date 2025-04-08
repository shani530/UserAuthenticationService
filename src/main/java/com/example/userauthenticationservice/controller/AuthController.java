package com.example.userauthenticationservice.controller;

import com.example.userauthenticationservice.dtos.UserDto;
import com.example.userauthenticationservice.model.User;
import com.example.userauthenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController
{
    @Autowired
    UserService userService;
    // add signup , signout and signin methods here
    @PostMapping("/signup")
    public String signup(@RequestBody UserDto userDto)
    {
        User user = new User();
        user.setUsername(userDto.getUsername());
         // add password hashing here
         // for now we will use plain text password
         // in production use bcrypt or any other hashing algorithm
         // to hash the password
         // user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
         userService.saveUser(user);
        return "User signed successfully";
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
        userService.signout();
        return "signout successfully";
    }

}
