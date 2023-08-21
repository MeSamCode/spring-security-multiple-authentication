package com.cleancodeaddict.springsecurtymultipleauthentication.security.filters;

import com.cleancodeaddict.springsecurtymultipleauthentication.security.authentications.APIKeyAuthentication;
import com.cleancodeaddict.springsecurtymultipleauthentication.security.manager.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class APIKeyAuthenticationFilter extends OncePerRequestFilter {
    private final CustomAuthenticationManager manager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var headerKey = request.getHeader("x-api-key");
        if (headerKey == null || "null".equals(headerKey)) {
            filterChain.doFilter(request, response);
        }
        APIKeyAuthentication keyAuthentication = new APIKeyAuthentication(headerKey, false);
        try {
            var authResult = manager.authenticate(keyAuthentication);
            if (authResult.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(keyAuthentication);
                filterChain.doFilter(request, response);
            }

        } catch (AuthenticationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
