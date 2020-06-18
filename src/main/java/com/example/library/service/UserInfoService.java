package com.example.library.service;

import com.example.library.model.UserInfo;
import com.example.library.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public UserInfo getUserByUserName(String username) {
        return userInfoRepository.findByUsername(username);
    }

    public UserInfo getUserByUserId(int userId) {
        return userInfoRepository.findByUserId(userId);
    }

}
