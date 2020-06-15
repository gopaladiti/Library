package com.example.library.service;

import com.example.library.model.UserDetails;
import com.example.library.repositories.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public UserDetails getUserByUserName(String username) {
        return userDetailsRepository.findByUsername(username);
    }

    public UserDetails getUserByUserId(int userId) {
        return userDetailsRepository.findByUserId(userId);
    }

}
