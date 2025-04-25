package com.example.userauthenticationservice.service;

import com.example.userauthenticationservice.model.User;
import com.example.userauthenticationservice.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    UserRepo  userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        UserDetail userDetail = new UserDetail();
        userDetail.setUsername(user.getUsername());
        userDetail.setPassword(user.getPassword());
        return userDetail;

    }
    // add a method to save the user



}
