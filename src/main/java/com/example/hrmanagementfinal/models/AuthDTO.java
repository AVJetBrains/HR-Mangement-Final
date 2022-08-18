package com.example.hrmanagementfinal.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthDTO {

    private String authId;
    private String token;
    private String userId;
}
