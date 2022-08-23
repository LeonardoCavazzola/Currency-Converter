package com.converter.api.common

import org.springframework.security.core.context.SecurityContextHolder

interface UserRetrievable {
    val authenticatedUserId: String
        get() = SecurityContextHolder.getContext()?.authentication?.principal as String? ?: "Anonymous user"
}
