package com.converter.api.controller

import com.converter.api.components.hateoas.UserLinkFactory
import com.converter.api.dto.UserForm
import com.converter.api.dto.UserView
import com.converter.api.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
    private val userLinkFactory: UserLinkFactory
) {

    @PostMapping
    @Transactional
    fun create(
        @RequestBody @Valid form: UserForm,
        builder: UriComponentsBuilder
    ): ResponseEntity<UserView> {

        val user = userService.create(
            email = form.email!!,
            password = form.password!!
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