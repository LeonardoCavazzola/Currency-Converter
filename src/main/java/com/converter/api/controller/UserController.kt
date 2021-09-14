package com.converter.api.controller;

import com.converter.api.components.hateoas.UserLinkFactory;
import com.converter.api.dto.UserForm;
import com.converter.api.dto.UserView;
import com.converter.api.model.User;
import com.converter.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserLinkFactory userLinkFactory;

    public UserController(UserService userService, UserLinkFactory userLinkFactory) {
        this.userService = userService;
        this.userLinkFactory = userLinkFactory;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UserView> create(@Valid @RequestBody UserForm form, UriComponentsBuilder builder) {
        User user = userService.create(new User(form.email(), form.password()));

        URI uri = builder
                .path("/users")
                .buildAndExpand(user.getId())
                .toUri();

        UserView userView = new UserView(user);
        userView.add(userLinkFactory.getAuthLink());

        return ResponseEntity.created(uri).body(userView);
    }
}
