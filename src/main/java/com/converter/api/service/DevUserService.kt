package com.converter.api.service;

import com.converter.api.model.User;
import com.converter.api.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("dev")
@Service
public class DevUserService extends AbstractUserService {

    public DevUserService(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public User getLoggedUser() {
        return userRepository.getById(1L);
    }
}
