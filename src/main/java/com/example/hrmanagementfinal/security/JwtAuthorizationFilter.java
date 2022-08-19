package com.example.hrmanagementfinal.security;

import com.example.hrmanagementfinal.models.AuthDTO;
import com.example.hrmanagementfinal.models.UserDTO;
import com.example.hrmanagementfinal.services.AuthService;
import com.example.hrmanagementfinal.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.MalformedParametersException;
import java.util.Arrays;
import java.util.Date;

import static com.example.hrmanagementfinal.security.SecurityConstants.HEADER_STRING;
import static com.example.hrmanagementfinal.security.SecurityConstants.TOKEN_PREFIX;
import static java.security.KeyRep.Type.SECRET;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserService userService;

    private AuthService authService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        ServletContext servletContext = req.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils
                .getWebApplicationContext(servletContext);
        if (userService == null) {
            userService = (UserService) webApplicationContext.getBean("userService");
        }

        if (authService == null) {
            authService = (AuthService) webApplicationContext.getBean("authService");
        }

        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
//            authentication = (UsernamePasswordAuthenticationToken) super.getAuthenticationManager().authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("Authentication = " + authentication.getName());
            chain.doFilter(req, res);
        } catch (MalformedParametersException | BadCredentialsException ex) {
            res.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
        String authorizationHeader = req.getHeader(HEADER_STRING);
        String token = "";
        System.out.println("Authorization header = " + authorizationHeader);
        if (authorizationHeader != null) {
            if (authorizationHeader.startsWith(TOKEN_PREFIX)) {
                token = authorizationHeader.replace(TOKEN_PREFIX, "");
                System.out.println("Token = " + token);
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET.getBytes())
                        .parseClaimsJws(token)
                        .getBody();

                Date expirationDate = claims.getExpiration();
                if (expirationDate.after(new Date(System.currentTimeMillis()))) {
                    AuthDTO authDTO = authService.findByToken(token);
                    System.out.println("claims.getAudience = " + claims.getAudience());
                    System.out.println("authDTO = " + authDTO.getUserId());
                    if (authDTO != null) {
                        UserDTO userDTO = userService.getUserByEmail(claims.getSubject());
                        System.out.println("userDTO = " + userDTO.getRole());
                        if (userDTO != null) {
                            return new UsernamePasswordAuthenticationToken(userDTO,
                                    null, Arrays.asList(new SimpleGrantedAuthority(userDTO.getRole().toString())));
                        }
                    }
                }
            }
        }
        return new UsernamePasswordAuthenticationToken(null, null,
                Arrays.asList(new SimpleGrantedAuthority(Roles.Anonymous)));
    }
}
