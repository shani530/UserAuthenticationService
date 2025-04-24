package com.example.userauthenticationservice.service;

import com.example.userauthenticationservice.model.LoginResponse;
import com.example.userauthenticationservice.model.User;
import org.springframework.data.util.Pair;

public interface IAuthService {
    User signUp(String email, String password,String firstName, String lastName, String username) throws Exception;
    LoginResponse login(String email, String password) throws Exception;
    String logout(String token) throws Exception;
}
