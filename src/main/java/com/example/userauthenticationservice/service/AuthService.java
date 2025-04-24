package com.example.userauthenticationservice.service;

import com.example.userauthenticationservice.exception.UnAuthorizedException;
import com.example.userauthenticationservice.model.LoginResponse;
import com.example.userauthenticationservice.model.User;
import com.example.userauthenticationservice.repo.UserRepo;
import lombok.Setter;
import org.apache.kafka.common.security.auth.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements IAuthService {
    // Implement the methods from IAuthService interface

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    @Override
    public User signUp(String email, String password, String firstName, String lastName, String username) throws Exception {
        // Check if the user already exists
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if (optionalUser.isPresent()) {
            throw new Exception("User already exists");
        }



        // Create a new user
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);

        // Save the user to the database
        return userRepo.save(user);
    }

    @Override
    public LoginResponse login(String email, String password) throws Exception {
        // Implementation for logging in a user
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isEmpty()) {
            throw new Exception("User not found");
        }
        if (user.isPresent() && !passwordEncoder.matches(password, user.get().getPassword())) {
            throw new UnAuthorizedException("Invalid password");
        }
        // Generate a token (this is just a placeholder, you should implement proper token generation)
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken("generated_token");
        loginResponse.setUsername(user.get().getUsername());
        return loginResponse;

    }

    @Override
    public String logout(String token) throws Exception {
        return null;
    }

    /*@Override
    public Boolean validateToken(String token, Long userId) throws Exception {
        // Implementation for validating a token
        return null;
    }*/
}
