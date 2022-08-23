package com.converter.api.exception

class CurrencyDoesntExistOrArentAvailableException : Exception() {
    override val message: String
        get() = "One or more currencies do not exist or are not available"
}
