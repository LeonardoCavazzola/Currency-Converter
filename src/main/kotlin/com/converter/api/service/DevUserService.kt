package com.converter.api.service

import com.converter.api.model.User
import com.converter.api.repository.UserRepository

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Profile("dev")
@Service
class DevUserService(
    userRepository: UserRepository
) : AbstractUserService(userRepository) {

    override fun getLoggedUser(): User {
        return userRepository.getById(1L)
    }
}