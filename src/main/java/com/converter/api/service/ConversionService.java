package com.converter.api.service;

import com.converter.api.dto.RatesResponse;
import com.converter.api.exception.CurrenciesDontExistOrArentAvailableException;
import com.converter.api.model.Conversion;
import com.converter.api.repository.ConversionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ConversionService {

    private final RestTemplate client = new RestTemplate();
    private final ConversionRepository conversionRepository;
    private final UserService userService;

    @Value("${api.key}")
    private String key;

    public ConversionService(ConversionRepository conversionRepository, UserService userService) {
        this.conversionRepository = conversionRepository;
        this.userService = userService;
    }

    private RatesResponse getRates(String... currencies) {

        String path = "http://api.exchangeratesapi.io" +
                "/v1" +
                "/latest" +
                "?access_key=" + key +
                "&symbols=";

        AtomicReference<String> url = new AtomicReference<>(path);
        Arrays.stream(currencies).forEach(s -> url.set(url + s + ","));

        ResponseEntity<RatesResponse> response = client.exchange(url.get(),
                HttpMethod.GET,
                null,
                RatesResponse.class);

        return response.getBody();
    }

    public Conversion convert(String originCurrency, String destinyCurrency, BigDecimal originValue) {

        Map<String, BigDecimal> rates = getRates(originCurrency, destinyCurrency).rates();

        BigDecimal originRate = rates.get(originCurrency);
        BigDecimal destinyRate = rates.get(destinyCurrency);

        try {
            BigDecimal convertionRateResult = destinyRate.divide(originRate, RoundingMode.HALF_UP);
            return new Conversion(userService.getLoggedUser(), originCurrency, originValue, destinyCurrency, convertionRateResult);
        } catch (NullPointerException e) {
            throw new CurrenciesDontExistOrArentAvailableException();
        }
    }

    public Conversion create(Conversion conversion){
        return conversionRepository.save(conversion);
    }
}
