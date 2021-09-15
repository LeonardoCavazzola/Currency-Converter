package com.converter.api.service

import com.converter.api.model.User
import com.converter.api.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
abstract class AbstractUserService(
    protected val userRepository: UserRepository
) : UserService {

    override fun create(email: String, password: String): User {
        return userRepository.save(
            User(
                email = email,
                password = BCryptPasswordEncoder().encode(password)
            )
        )
    }
}