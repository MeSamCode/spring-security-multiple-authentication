package com.cleancodeaddict.springsecurtymultipleauthentication.security.providers;

import com.cleancodeaddict.springsecurtymultipleauthentication.entities.AppUser;
import com.cleancodeaddict.springsecurtymultipleauthentication.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var username=authentication.getName();
        var presentedPassword=authentication.getCredentials().toString();
       AppUser user= userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No such user"));
       if (encoder.matches(presentedPassword,user.getPassword())){
           return new UsernamePasswordAuthenticationToken(username,user.getPassword());
       }
        throw new BadCredentialsException("Bad credentials!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
