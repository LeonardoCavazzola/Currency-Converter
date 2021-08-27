package com.converter.api.service;

import com.converter.api.model.User;
import com.converter.api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractUserService implements UserService{

    protected final UserRepository userRepository;

    public AbstractUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }
}
