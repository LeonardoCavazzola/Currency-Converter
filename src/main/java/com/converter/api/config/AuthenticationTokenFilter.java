package com.converter.api.config;

import com.converter.api.model.User;
import com.converter.api.repository.UserRepository;
import com.converter.api.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    public AuthenticationTokenFilter(TokenService tokenService, UserRepository repository) {
        this.tokenService = tokenService;
        this.userRepository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = decryptToken(request);
        boolean valid = tokenService.isValidToken(token);
        if (valid) {
            autenticate(token);
        }

        filterChain.doFilter(request, response);
    }

    private void autenticate(String token) {
        Long userId = tokenService.getUserId(token);
        User user = userRepository.getById(userId);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String decryptToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }
}
