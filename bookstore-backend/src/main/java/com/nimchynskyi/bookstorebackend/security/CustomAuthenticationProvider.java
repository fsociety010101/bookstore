package com.nimchynskyi.bookstorebackend.security;

import com.nimchynskyi.bookstorebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UserRepository repository;
    public CustomAuthenticationProvider() {
        super();
    }


    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        System.out.println("authenticate:");
        System.out.println(name);
        System.out.println(password);
        if (shouldAuthenticateAgainstThirdPartySystem(name, password)) {
            var user = repository.findByEmail(name);
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new UsernamePasswordAuthenticationToken(
                    name, password, user.get().getAuthorities());
        } else {
            throw  new BadCredentialsException("nie poprawne logowanie");
        }
    }

    private boolean shouldAuthenticateAgainstThirdPartySystem(String name, String password) {



        var userOptional = repository.findByEmail(name);


        if(userOptional.isPresent()){
            System.out.println("jest taki user");
            if(userOptional.get().getPassword().equals(password)){

                return true;
            }
        }
        System.out.println("nie jest taki user");
        return false;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}