package com.converter.api.config

import com.converter.api.repository.UserRepository
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.FilterChain
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import java.util.NoSuchElementException
import com.converter.api.exception.TokenException
import com.converter.api.service.TokenService

class AuthenticationTokenFilter(
    private val tokenService: TokenService,
    private val userRepository: UserRepository
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val token = recoverToken(request)
        if (tokenService.isValidToken(token)) {
            autenticateUser(token!!)
        }
        filterChain.doFilter(request, response)
    }

    private fun autenticateUser(token: String) {
        val userId = tokenService.getUserId(token)

        try {
            val user = userRepository.findById(userId).get()
            val authentication = UsernamePasswordAuthenticationToken(user, null, user.authorities)
            SecurityContextHolder.getContext().authentication = authentication
        } catch (e: NoSuchElementException) {
            throw TokenException()
        }
    }

    private fun recoverToken(request: HttpServletRequest): String? {

        val token = request.getHeader("Authorization")

        return if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            null
        } else
            token.substring(7, token.length)
    }
}