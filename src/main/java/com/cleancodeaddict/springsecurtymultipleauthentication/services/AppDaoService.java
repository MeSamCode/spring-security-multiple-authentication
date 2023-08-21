package com.cleancodeaddict.springsecurtymultipleauthentication.services;

import com.cleancodeaddict.springsecurtymultipleauthentication.entities.AppUser;
import com.cleancodeaddict.springsecurtymultipleauthentication.entities.Authority;
import com.cleancodeaddict.springsecurtymultipleauthentication.repositories.AuthorityRepository;
import com.cleancodeaddict.springsecurtymultipleauthentication.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor

public class AppDaoService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder encoder;

    public AppUser addNewUser(AppUser user) {
        if (userRepository.findByUsername(user.getUsername()).isEmpty()) {
            user.setPassword(encoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        throw new RuntimeException("A user with this username already exists!");
    }

    public String addNewAuth(String authName) {
        if (authorityRepository.findByAuthName(authName).isEmpty()) {
            Authority authority = Authority.builder()
                    .authName(authName)
                    .build();
            authorityRepository.save(authority);
            return "new authority name was added successfully";
        }
        return "This authority name is already exists.";
    }

    public String addAuthToUser(String username,String AuthName){
        Authority authority =authorityRepository.findByAuthName(AuthName)
                .orElseThrow(() -> new NullPointerException("The authority name doesn't exists."));
        AppUser user=userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No such user exists! "));
        boolean result=user.getAuthorities().add(authority);
        if (result){
            return "Successful operation!";
        }else {
            return "The authority was already granted to this user";
        }
    }
}
