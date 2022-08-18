package com.example.hrmanagementfinal.services;

import com.example.hrmanagementfinal.accessor.LoginAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class LoginServices {

    @Autowired
    private LoginAccessor loginAccessor;



}
