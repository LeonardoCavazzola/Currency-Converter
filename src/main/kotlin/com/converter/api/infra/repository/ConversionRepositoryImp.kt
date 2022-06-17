package com.converter.api.infra.repository

import com.converter.api.domain.entity.Conversion
import com.converter.api.domain.entity.User
import com.converter.api.domain.repository.ConversionRepository
import com.converter.api.infra.model.ConversionModel
import com.converter.api.infra.model.UserModel
import com.converter.api.infra.model.toEntity
import com.converter.api.infra.model.toModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
class ConversionRepositoryImp(
    val modelRepository: ConversionModelRepository
) : ConversionRepository {
    fun save(conversion: Conversion) = conversion.also { modelRepository.save(conversion.toModel()) }
    override fun findAllByUser(user: User, pageable: Pageable): Page<Conversion> =
        modelRepository.findAllByUser(user.toModel(), pageable).map { it.toEntity() }
}

interface ConversionModelRepository : JpaRepository<ConversionModel, Long> {
    fun findAllByUser(user: UserModel, pageable: Pageable): Page<ConversionModel>
}
