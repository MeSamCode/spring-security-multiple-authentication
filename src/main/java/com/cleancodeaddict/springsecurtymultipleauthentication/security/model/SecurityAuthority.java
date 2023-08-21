package com.cleancodeaddict.springsecurtymultipleauthentication.security.model;

import com.cleancodeaddict.springsecurtymultipleauthentication.entities.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
@RequiredArgsConstructor
public class SecurityAuthority implements GrantedAuthority {
    private final Authority authority;
    @Override
    public String getAuthority() {
        return  authority.getAuthName();
    }
}
