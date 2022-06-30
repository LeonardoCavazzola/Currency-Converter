package com.converter.api.domain.gateway

import com.converter.api.domain.entity.Rates

interface RatesClient {
    fun getRates(vararg currencies: String): Rates
}
