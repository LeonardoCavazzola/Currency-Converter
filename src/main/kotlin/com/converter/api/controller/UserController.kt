package com.converter.api.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import com.converter.api.service.UserService
import com.converter.api.components.hateoas.UserLinkFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import javax.validation.Valid
import com.converter.api.dto.UserForm
import org.springframework.web.util.UriComponentsBuilder
import org.springframework.http.ResponseEntity
import com.converter.api.dto.UserView
import com.converter.api.model.User
import javax.transaction.Transactional

@RestController
@RequestMapping("/users")
open class UserController(
    private val userService: UserService,
    private val userLinkFactory: UserLinkFactory
) {

    @PostMapping
    @Transactional
    open fun create(@RequestBody form: @Valid UserForm, builder: UriComponentsBuilder): ResponseEntity<UserView> {

        val user = userService.create(
            User(
                email = form.email,
                password = form.password
            )
        )
        val uri = builder
            .path("/users")
            .buildAndExpand(user.id)
            .toUri()

        val userView = UserView(user = user)
        userView.add(userLinkFactory.authenticate())
        return ResponseEntity.created(uri).body(userView)
    }
}