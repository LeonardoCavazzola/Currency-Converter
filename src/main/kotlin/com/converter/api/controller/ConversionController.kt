package com.converter.api.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import com.converter.api.components.hateoas.ConversionLinkFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.util.UriComponentsBuilder
import org.springframework.hateoas.PagedModel
import org.springframework.hateoas.EntityModel
import com.converter.api.dto.ConversionView
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import javax.validation.Valid
import com.converter.api.dto.ConversionForm
import com.converter.api.model.Conversion
import com.converter.api.service.ConversionService
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import javax.transaction.Transactional

@RestController
@RequestMapping("/convertions")
open class ConversionController(
    private val conversionService: ConversionService,
    private val conversionLinkFactory: ConversionLinkFactory
) {

    @GetMapping
    fun getAll(
        pageable: Pageable?,
        builder: UriComponentsBuilder?
    ): PagedModel<EntityModel<ConversionView>> {

        val page = conversionService.getMyConvetions(pageable)
            .map { conversion: Conversion ->
                ConversionView(conversion = conversion)
            }

        val pra = PagedResourcesAssembler<ConversionView>(
            HateoasPageableHandlerMethodArgumentResolver(),
            builder?.path("/convertions")?.build()
        )

        val pagedModel = pra.toModel(page)
        pagedModel.add(conversionLinkFactory.convert())

        return pagedModel
    }

    @PostMapping
    @Transactional
    open fun convert(
        @RequestBody @Valid form: ConversionForm?,
        builder: UriComponentsBuilder?
    ): ResponseEntity<ConversionView> {

        var converted = conversionService.convert(form!!.originCurrency, form.destinyCurrency, form.originValue)
        converted = conversionService.create(converted)

        val conversionView = ConversionView(converted)

        val uri = builder
            ?.path("/convertions")
            ?.buildAndExpand(converted.id)
            ?.toUri()

        conversionView.add(conversionLinkFactory.getAllMyConversions())
        conversionView.add(conversionLinkFactory.convert())

        return ResponseEntity.created(uri!!).body(conversionView)
    }
}