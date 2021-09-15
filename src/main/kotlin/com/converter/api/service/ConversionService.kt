package com.converter.api.service

import com.converter.api.dto.RatesResponse
import com.converter.api.exception.CurrenciesDontExistOrArentAvailableException
import com.converter.api.model.Conversion
import com.converter.api.repository.ConversionRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import java.util.concurrent.atomic.AtomicReference

@Service
class ConversionService(
    private val conversionRepository: ConversionRepository,
    private val userService: UserService,
    private val client: RestTemplate
) {

    @Value("\${api.key}")
    private lateinit var key: String

    fun getRates(vararg currencies: String): RatesResponse {

        val path = "http://api.exchangeratesapi.io" +
                "/v1" +
                "/latest" +
                "?access_key=" +
                key +
                "&symbols="

        val url = AtomicReference(path)
        Arrays.stream(currencies).forEach { s: String -> url.set("$url$s,") }

        val response = client.exchange(
            url.get(),
            HttpMethod.GET,
            null,
            RatesResponse::class.java
        )

        return response.body!!
    }

    fun getMyConvetions(pageable: Pageable?): Page<Conversion> {
        return conversionRepository.findAllByUser(userService.getLoggedUser(), pageable)
    }

    fun convert(originCurrency: String, destinyCurrency: String, originValue: BigDecimal): Conversion {

        val rates = getRates(originCurrency, destinyCurrency).rates
        val originRate = rates[originCurrency]
        val destinyRate = rates[destinyCurrency]

        val convertionRateResult = destinyRate?.divide(originRate, 6, RoundingMode.HALF_UP)

        convertionRateResult ?: throw CurrenciesDontExistOrArentAvailableException()

        return Conversion(
            user = userService.getLoggedUser(),
            originCur = originCurrency,
            originValue = originValue,
            destinyCur = destinyCurrency,
            conversionRate = convertionRateResult
        )
    }

    fun create(conversion: Conversion): Conversion {
        return conversionRepository.save(conversion)
    }
}