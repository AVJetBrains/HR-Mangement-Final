package com.example.hrmanagementfinal.services;

import com.example.hrmanagementfinal.Exception.UserNotFoundException;
import com.example.hrmanagementfinal.accessor.EmployeeAccessor;
import com.example.hrmanagementfinal.models.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeServices {

    @Autowired
    private EmployeeAccessor employeeAccessor;


    public void getEmployees(){
        employeeAccessor.getEmployees();
    }

    // code for generating link

    public void sendResetPasswordLink(String email) {
        UserDTO userDTO = employeeAccessor.getUserByEmail(email);

        if (userDTO == null) {
            throw new UserNotFoundException(email);
        }
        // code for sending the email
    }

    public boolean updatePassword(String userId, String newPassword) {
        return employeeAccessor.updatePassword(userId, newPassword);
    }

    public UserDTO getEmployeeByName(String name) {
        return employeeAccessor.getUserByName(name);
    }
}
