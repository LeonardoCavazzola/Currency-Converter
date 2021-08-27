package com.converter.api.controller;

import com.converter.api.dto.ConversionForm;
import com.converter.api.dto.ConversionView;
import com.converter.api.model.Conversion;
import com.converter.api.service.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class ConversionController {

    private final ConversionService conversionService;

    public ConversionController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ConversionView> convert(@Valid @RequestBody ConversionForm form) {
        Conversion converted = conversionService.convert(form.originCurrency(), form.destinyCurrency(), form.originValue());


        return ResponseEntity.ok(new ConversionView(converted));
    }
}
