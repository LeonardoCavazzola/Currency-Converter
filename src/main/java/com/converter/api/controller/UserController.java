package com.converter.api.controller;

import com.converter.api.dto.UserForm;
import com.converter.api.dto.UserView;
import com.converter.api.model.User;
import com.converter.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UserView> create(@Valid @RequestBody UserForm form, UriComponentsBuilder builder) {
        User user = userService.create(form);

        URI uri = builder
                .path("/users")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new UserView(user));
    }
}
