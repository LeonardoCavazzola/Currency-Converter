package com.converter.api.service;

import com.converter.api.dto.UserForm;
import com.converter.api.model.User;
import com.converter.api.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(UserForm form) {
        String pass = new BCryptPasswordEncoder().encode(form.password());
        User user = new User(form.email(), pass);
        return userRepository.save(user);
    }
}
