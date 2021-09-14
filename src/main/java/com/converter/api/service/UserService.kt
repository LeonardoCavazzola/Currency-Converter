package com.converter.api.service

import com.converter.api.model.User
import org.springframework.stereotype.Service

@Service
interface UserService {

    fun getLoggedUser(): User
    fun create(user: User): User
}