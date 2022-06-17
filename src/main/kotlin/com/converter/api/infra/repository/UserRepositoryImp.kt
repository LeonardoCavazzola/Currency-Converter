package com.converter.api.infra.repository

import com.converter.api.domain.entity.User
import com.converter.api.domain.repository.UserRepository
import com.converter.api.infra.model.UserModel
import com.converter.api.infra.model.toEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImp(
    val modelRepository: UserModelRepository
) : UserRepository {
    override fun findByEmail(email: String): User? = modelRepository.findByEmail(email)?.toEntity()

    override fun existsByEmail(email: String): Boolean = modelRepository.existsByEmail(email)
}

interface UserModelRepository : JpaRepository<UserModel, Long> {
    fun findByEmail(email: String): UserModel?
    fun existsByEmail(email: String): Boolean
}
