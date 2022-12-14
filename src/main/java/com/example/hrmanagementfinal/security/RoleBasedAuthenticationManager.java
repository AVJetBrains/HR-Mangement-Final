package com.example.hrmanagementfinal.security;

import com.example.hrmanagementfinal.models.UserDTO;
import com.example.hrmanagementfinal.services.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class RoleBasedAuthenticationManager implements AuthenticationManager {

    private final UserServices userService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = (String) authentication.getPrincipal();
        List<GrantedAuthority> allowedRoles = new ArrayList<>(authentication.getAuthorities());
        UserDTO userDTO = userService.getUserByEmail(email);
        if (email == null || userDTO == null) {

        }
        // check if any of the allowed roles match with the role in the user
        for(int i = 0; i < allowedRoles.size(); i++) {
            if (allowedRoles.get(i).equals(userDTO.getRole().toString())) {
                return new UsernamePasswordAuthenticationToken(
                        new User(email, "", allowedRoles), "", allowedRoles);
            }
        }
        throw new BadCredentialsException("Role " + userDTO.getRole().toString() + " is not allowed!");
        }
}
