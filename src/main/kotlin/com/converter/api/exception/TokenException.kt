package com.converter.api.exception

import java.lang.RuntimeException

class TokenException : RuntimeException() {

    override val message: String
        get() = "Invalid token"
}