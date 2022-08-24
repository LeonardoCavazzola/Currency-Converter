package com.converter.api.controller

import com.converter.api.domain.service.ConversionService
import com.converter.api.controller.dto.ConversionIntentInput
import com.converter.api.controller.dto.ConversionOutput
import com.converter.api.controller.dto.toEntity
import com.converter.api.controller.dto.toOutput
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/conversions")
class ConversionController(
    private val conversionService: ConversionService,
) {
    @GetMapping
    fun getAll(pageable: Pageable): Page<ConversionOutput> =
        conversionService.getMyConversions(pageable).map { it.toOutput() }

    @PostMapping
    fun convert(
        @RequestBody @Valid input: ConversionIntentInput,
        builder: UriComponentsBuilder
    ): ResponseEntity<ConversionOutput> {
        val conversionOutput = conversionService.convert(input.toEntity()).toOutput()
        val uri = builder
            .path("/conversions")
            .buildAndExpand(conversionOutput.id)
            .toUri()
        return ResponseEntity.created(uri).body(conversionOutput)
    }
}
