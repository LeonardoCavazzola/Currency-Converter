package com.converter.api.service

import com.converter.api.model.User
import com.converter.api.repository.UserRepository
import com.converter.api.service.UserService
import org.springframework.stereotype.Service

@Service
abstract class AbstractUserService(
    protected val userRepository: UserRepository) : UserService {

    override fun create(user: User): User {
        return userRepository.save(user)
    }
}