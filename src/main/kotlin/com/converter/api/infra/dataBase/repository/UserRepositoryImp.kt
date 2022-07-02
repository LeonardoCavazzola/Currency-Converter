package com.converter.api.infra.dataBase.repository

import com.converter.api.domain.entity.User
import com.converter.api.domain.repository.UserRepository
import com.converter.api.infra.dataBase.model.UserModel
import com.converter.api.infra.dataBase.model.toEntity
import com.converter.api.infra.dataBase.model.toModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImp(
    val modelRepository: UserModelRepository
) : UserRepository {
    override fun findById(id: Long): User? = modelRepository.findByIdOrNull(id)?.toEntity()

    override fun findByEmail(email: String): User? = modelRepository.findByEmail(email)?.toEntity()

    override fun save(user: User): User = user.also { modelRepository.save(it.toModel()) }

    override fun existsByEmail(email: String): Boolean = modelRepository.existsByEmail(email)
}

interface UserModelRepository : JpaRepository<UserModel, Long> {
    fun findByEmail(email: String): UserModel?
    fun existsByEmail(email: String): Boolean
}
