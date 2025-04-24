package com.example.userauthenticationservice.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;

}
