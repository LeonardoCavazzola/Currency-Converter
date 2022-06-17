package com.converter.api.infra.model

import com.converter.api.domain.entity.User
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class UserModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @Column(nullable = false) val email: String,
    @Column(nullable = false) val password: String,
)

fun UserModel.toEntity() = User(
    id = id,
    email = email,
    password = password
)

fun User.toModel() = UserModel(
    id = id,
    email = email,
    password = password
)
