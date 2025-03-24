package com.example.userauthenticationservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends BaseModel{
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;

}
