package com.converter.api.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import javax.validation.Valid
import com.converter.api.dto.AuthForm
import org.springframework.http.ResponseEntity
import com.converter.api.dto.AuthView
import com.converter.api.service.TokenService

@RestController
@RequestMapping("/auth")
class AuthenticationController(
    private val authManager: AuthenticationManager,
    private val tokenService: TokenService
) {

    @PostMapping
    fun authenticate(@RequestBody @Valid form: AuthForm? = null): ResponseEntity<AuthView> {
        val dadosLogin = form?.toUsernamePasswordAuthenticationToken()

        val authentication = authManager.authenticate(dadosLogin)
        val token = tokenService.generateToken(authentication)
        return ResponseEntity.ok(AuthView(token, "Bearer"))
    }
}