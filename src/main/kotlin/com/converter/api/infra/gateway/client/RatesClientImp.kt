package com.converter.api.infra.gateway.client

import com.converter.api.domain.entity.Rates
import com.converter.api.domain.gateway.RatesClient
import com.converter.api.infra.gateway.model.RatesResponse
import com.converter.api.infra.gateway.model.toEntity
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class RatesClientImp(
    private val client: RestTemplate,
    @Value("\${api.key}") private val key: String
) : RatesClient {

    override fun getRates(vararg currencies: String): Rates {
        val params = mapOf(
            "access_key" to key,
            "symbols" to currencies.toSymbols(),
        )

        val response = client.exchange(URL, HttpMethod.GET, null, RatesResponse::class.java, params)
        return response.body!!.toEntity()
    }

    private fun Array<out String>.toSymbols(): String {
        var symbols = ""
        forEach { symbols = "$symbols$it," }
        return symbols
    }

    companion object {
        const val URL = "http://api.exchangeratesapi.io/v1/latest?access_key={access_key}&symbols={symbols}"
    }
}



