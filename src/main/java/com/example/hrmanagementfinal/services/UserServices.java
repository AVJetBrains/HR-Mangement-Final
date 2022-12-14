package com.example.hrmanagementfinal.services;

import com.example.hrmanagementfinal.Exception.UserNotFoundException;
import com.example.hrmanagementfinal.accessor.UserAccessor;
import com.example.hrmanagementfinal.models.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServices {

    @Autowired
    private UserAccessor userAccessor;


    public void sendResetPasswordLink(String email) {
        UserDTO userDTO = userAccessor.getUserByEmail(email);

        if (userDTO == null) {
            throw new UserNotFoundException(email);
        }

        // Write the code for generating link
        // Write the code for sending the email
    }

    public boolean updatePassword(String userId, String newPassword) {
        return userAccessor.updatePassword(userId, newPassword);
    }

    public UserDTO getUserByEmail(String email) {
        return userAccessor.getUserByEmail(email);
    }
}
