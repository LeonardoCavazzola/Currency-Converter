package com.converter.api.service

import com.converter.api.common.UserRetrievable
import com.converter.api.domain.entity.Conversion
import com.converter.api.domain.entity.ConversionIntent
import com.converter.api.domain.gateway.RatesClient
import com.converter.api.domain.repository.ConversionRepository
import com.converter.api.domain.service.ConversionService
import com.converter.api.exception.CurrencyDoesntExistOrArentAvailableException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ConversionServiceImp(
    private val conversionRepository: ConversionRepository,
    private val ratesClient: RatesClient,
) : ConversionService, UserRetrievable {
    override fun getMyConversions(pageable: Pageable): Page<Conversion> =
        conversionRepository.findAllByUserId(authenticatedUserId, pageable)

    override fun convert(intent: ConversionIntent): Conversion {
        intent.run {
            val rates = ratesClient.getRates(originCurrency, destinyCurrency).rates
            val originRate = rates[originCurrency] ?: throw CurrencyDoesntExistOrArentAvailableException()
            val destinyRate = rates[destinyCurrency] ?: throw CurrencyDoesntExistOrArentAvailableException()
            val conversion = intent.convert(originRate, destinyRate)
            return conversionRepository.save(conversion)
        }
    }
}
