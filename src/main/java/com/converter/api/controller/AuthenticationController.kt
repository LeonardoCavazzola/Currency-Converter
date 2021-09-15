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
import org.springframework.security.core.AuthenticationException

@RestController
@RequestMapping("/auth")
class AuthenticationController(
    private val authManager: AuthenticationManager,
    private val tokenService: TokenService
) {

    @PostMapping
    fun authenticate(@RequestBody @Valid form: AuthForm?): ResponseEntity<AuthView> {
        val dadosLogin = form?.toUsernamePasswordAuthenticationToken()

        return try {
            val authentication = authManager.authenticate(dadosLogin)
            val token = tokenService.generateToken(authentication)
            ResponseEntity.ok(AuthView(token, "Bearer"))

        } catch (e: AuthenticationException) {
            ResponseEntity.badRequest().build()
        }
    }
}