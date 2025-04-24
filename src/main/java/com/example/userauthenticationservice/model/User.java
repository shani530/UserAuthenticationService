package com.example.userauthenticationservice.model;

import jakarta.persistence.Entity;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
public class User extends BaseModel{

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private  Status status;
    private  Role role;

}
