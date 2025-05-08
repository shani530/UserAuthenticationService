package com.example.userauthenticationservice.dtos;

import com.example.userauthenticationservice.model.Role;
import com.example.userauthenticationservice.model.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private Status status;
    private Role role;
}
