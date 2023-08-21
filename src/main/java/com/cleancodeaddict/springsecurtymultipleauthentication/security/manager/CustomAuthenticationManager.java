package com.cleancodeaddict.springsecurtymultipleauthentication.security.manager;

import com.cleancodeaddict.springsecurtymultipleauthentication.security.providers.APIKeyAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CustomAuthenticationManager implements AuthenticationManager{
    private final APIKeyAuthenticationProvider provider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (provider.supports( authentication.getClass())){
            return provider.authenticate(authentication);
        }
        throw new BadCredentialsException("Oh nooooo! You provided a bad credentials .Try again.");
    }
}
