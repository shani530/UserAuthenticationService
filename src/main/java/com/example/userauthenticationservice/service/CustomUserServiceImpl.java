package com.example.userauthenticationservice.service;

import com.example.userauthenticationservice.model.Role;
import com.example.userauthenticationservice.model.User;
import com.example.userauthenticationservice.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserServiceImpl implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }


        User user = optionalUser.get();

        return new CustomerUserDetail(user);

    }

    public User getUser(Long id) {
        // get the user from the database
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isEmpty()) {
            return null;
        }
        User user = optionalUser.get();
        return user;

    }
}

    // add a method to save the user




