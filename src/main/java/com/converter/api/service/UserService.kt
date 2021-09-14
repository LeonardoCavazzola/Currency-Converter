package com.converter.api.service;

import com.converter.api.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User getLoggedUser();
    User create(User user);
}
