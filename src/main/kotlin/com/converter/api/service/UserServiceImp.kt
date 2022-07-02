package com.converter.api.service

import com.converter.api.domain.entity.User
import com.converter.api.domain.repository.UserRepository
import com.converter.api.domain.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImp(
    private val userRepository: UserRepository
) : UserService {
    override fun findById(id: Long): User? = userRepository.findById(id)

    override fun create(user: User): User = userRepository.save(user)
}
