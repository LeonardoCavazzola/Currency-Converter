package com.converter.api.controller;

import com.converter.api.dto.ConversionForm;
import com.converter.api.dto.ConversionView;
import com.converter.api.model.Conversion;
import com.converter.api.service.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/convertions")
public class ConversionController {

    private final ConversionService conversionService;

    public ConversionController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @GetMapping
    public ResponseEntity<Page<ConversionView>> getAll(Pageable pageable){
        Page<ConversionView> page = conversionService.getMyConvetions(pageable).map(ConversionView::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ConversionView> convert(@Valid @RequestBody ConversionForm form, UriComponentsBuilder builder) {
        Conversion converted = conversionService.convert(form.originCurrency(), form.destinyCurrency(), form.originValue());
        converted = conversionService.create(converted);

        URI uri = builder
                .path("/convertions")
                .buildAndExpand(converted.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new ConversionView(converted));
    }
}
