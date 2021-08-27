package com.converter.api.controller;

import com.converter.api.components.hateoas.LinkFactory;
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
    private final LinkFactory linkFactory;

    public ConversionController(ConversionService conversionService, LinkFactory linkFactory) {
        this.conversionService = conversionService;
        this.linkFactory = linkFactory;
    }

    @GetMapping
    public PagedModel<EntityModel<ConversionView>> getAll(Pageable pageable, UriComponentsBuilder builder) {
        Page<ConversionView> page = conversionService.getMyConvetions(pageable).map(ConversionView::new);

        PagedResourcesAssembler<ConversionView> pra = new PagedResourcesAssembler<>(
                new HateoasPageableHandlerMethodArgumentResolver(),
                builder.path("/convertions").build());

        return pra.toModel(page);
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

        conversionView.add(linkFactory.getAllMyConversions());
        conversionView.add(linkFactory.convert());

        return ResponseEntity.created(uri).body(conversionView);
    }
}
