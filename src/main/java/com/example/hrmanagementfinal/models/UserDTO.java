package com.example.hrmanagementfinal.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserDTO {
    private String userId;
    private String name;
    private String email;
    private String password;
    private String phoneNo;
    private UserRoles role;
}
