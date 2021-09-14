package com.converter.api.controller;

import com.converter.api.components.hateoas.ConversionLinkFactory;
import com.converter.api.dto.ConversionForm;
import com.converter.api.dto.ConversionView;
import com.converter.api.model.Conversion;
import com.converter.api.service.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
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
    private final ConversionLinkFactory conversionLinkFactory;

    public ConversionController(ConversionService conversionService, ConversionLinkFactory conversionLinkFactory) {
        this.conversionService = conversionService;
        this.conversionLinkFactory = conversionLinkFactory;
    }

    @GetMapping
    public PagedModel<EntityModel<ConversionView>> getAll(Pageable pageable, UriComponentsBuilder builder) {
        Page<ConversionView> page = conversionService.getMyConvetions(pageable).map(ConversionView::new);

        PagedResourcesAssembler<ConversionView> pra = new PagedResourcesAssembler<>(
                new HateoasPageableHandlerMethodArgumentResolver(),
                builder.path("/convertions").build());

        PagedModel<EntityModel<ConversionView>> pagedModel = pra.toModel(page);
        pagedModel.add(conversionLinkFactory.convert());

        return pagedModel;

    }

    @PostMapping
    @Transactional
    public ResponseEntity<ConversionView> convert(@Valid @RequestBody ConversionForm form, UriComponentsBuilder builder) {
        Conversion converted = conversionService.convert(form.originCurrency(), form.destinyCurrency(), form.originValue());
        converted = conversionService.create(converted);

        ConversionView conversionView = new ConversionView(converted);

        URI uri = builder
                .path("/convertions")
                .buildAndExpand(converted.getId())
                .toUri();

        conversionView.add(conversionLinkFactory.getAllMyConversions());
        conversionView.add(conversionLinkFactory.convert());

        return ResponseEntity.created(uri).body(conversionView);
    }
}
