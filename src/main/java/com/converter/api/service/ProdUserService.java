package com.converter.api.service;

import com.converter.api.model.User;
import com.converter.api.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Profile("prod")
@Service
public class ProdUserService extends AbstractUserService{

    public ProdUserService(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public User getLoggedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
