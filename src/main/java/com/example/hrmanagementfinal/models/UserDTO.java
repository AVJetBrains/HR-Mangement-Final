package com.example.hrmanagementfinal.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String Email;
    private String password;
    private String role;
}
