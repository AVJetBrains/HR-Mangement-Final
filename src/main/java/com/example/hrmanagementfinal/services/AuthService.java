package com.example.hrmanagementfinal.services;

import com.example.hrmanagementfinal.accessor.AuthAccessor;
import com.example.hrmanagementfinal.models.AuthDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthService {

    @Autowired
    private AuthAccessor authAccessor;
    public AuthDTO findByToken(String token) {
        return authAccessor.findByToken(token);
    }

    public boolean storeToken(String userId, String token) {
        return authAccessor.storeToken(userId, token);
    }
}
