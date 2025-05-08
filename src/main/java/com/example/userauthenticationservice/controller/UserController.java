package com.example.userauthenticationservice.controller;

import com.example.userauthenticationservice.dtos.UserDto;
import com.example.userauthenticationservice.model.User;
import com.example.userauthenticationservice.service.CustomUserServiceImpl;
import com.example.userauthenticationservice.service.CustomerUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @Autowired
    CustomUserServiceImpl customUserServiceImpl;
    // get user by id
    @GetMapping("/user/{id}")
    public UserDto getUser(@PathVariable Long id) {

        User user =  customUserServiceImpl.getUser(id);
        return convertUserToUserDto(user);
    }

    private UserDto convertUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        userDto.setStatus(user.getStatus());
        return userDto;

    }

}
