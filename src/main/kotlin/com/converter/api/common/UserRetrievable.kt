package com.converter.api.common

import com.converter.api.domain.entity.User
import org.springframework.security.core.context.SecurityContextHolder

interface UserRetrievable {
    val authenticatedUser: User
        get() = SecurityContextHolder.getContext().authentication.principal as User
}
