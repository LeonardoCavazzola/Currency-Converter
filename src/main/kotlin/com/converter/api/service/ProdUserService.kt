package com.converter.api.service

import com.converter.api.model.User
import com.converter.api.repository.UserRepository
import org.springframework.context.annotation.Profile
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Profile("prod")
@Service
class ProdUserService(userRepository: UserRepository) :
    AbstractUserService(userRepository) {

    override fun getLoggedUser(): User {
        return SecurityContextHolder.getContext().authentication.principal as User
    }
}