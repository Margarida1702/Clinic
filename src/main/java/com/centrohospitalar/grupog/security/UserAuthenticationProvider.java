package com.centrohospitalar.grupog.security;

import com.centrohospitalar.grupog.entities.User;
import com.centrohospitalar.grupog.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider { //responsavel por criar o token

    @Autowired
    AuthenticationService authenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        User user = authenticationService.validateLogin(email, password);
        if(user != null) {
            List<SimpleGrantedAuthority> roleList = new ArrayList<>();
            roleList.add(new SimpleGrantedAuthority(user.getRole().getName()));
            return new UsernamePasswordAuthenticationToken(email, password, roleList);
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
