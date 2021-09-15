package com.converter.api.service

import com.converter.api.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val repository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {

        val user = repository.findByEmail(username)
        if (user.isPresent) {
            return user.get()
        }

        throw UsernameNotFoundException("Invalid data!")
    }
}