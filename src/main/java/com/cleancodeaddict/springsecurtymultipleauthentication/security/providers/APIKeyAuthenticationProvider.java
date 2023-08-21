package com.cleancodeaddict.springsecurtymultipleauthentication.security.providers;

import com.cleancodeaddict.springsecurtymultipleauthentication.security.authentications.APIKeyAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class APIKeyAuthenticationProvider implements AuthenticationProvider {
    @Value("${secret.key}")
    private String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        APIKeyAuthentication keyAuthentication = (APIKeyAuthentication) authentication;
        String headerKey = keyAuthentication.getApiKey();
        if (headerKey.equals(key)) {
            keyAuthentication.setAuthenticated(true);
            return keyAuthentication;
        }
        throw new BadCredentialsException("Oh nooooo! You provided a bad credentials .Try again.");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return APIKeyAuthentication.class.equals(authentication);
    }
}
