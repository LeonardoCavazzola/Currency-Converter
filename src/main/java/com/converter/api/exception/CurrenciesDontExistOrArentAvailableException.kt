package com.converter.api.exception

import java.lang.RuntimeException

class CurrenciesDontExistOrArentAvailableException : RuntimeException() {
    override val message: String
        get() = "One or more currencies do not exist or are not available"
}