package com.converter.api.infra.dataBase.repository

import com.converter.api.domain.entity.Conversion
import com.converter.api.domain.repository.ConversionRepository
import com.converter.api.infra.dataBase.model.ConversionModel
import com.converter.api.infra.dataBase.model.toEntity
import com.converter.api.infra.dataBase.model.toModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
class ConversionRepositoryImp(
    val modelRepository: ConversionModelRepository
) : ConversionRepository {
    override fun save(conversion: Conversion): Conversion = modelRepository.save(conversion.toModel()).toEntity()

    override fun findAllByUserId(userId: String, pageable: Pageable): Page<Conversion> =
        modelRepository.findAllByUserId(userId, pageable).map { it.toEntity() }
}

interface ConversionModelRepository : JpaRepository<ConversionModel, Long> {
    fun findAllByUserId(user: String, pageable: Pageable): Page<ConversionModel>
}
