package com.converter.api.model

import org.springframework.security.core.GrantedAuthority
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Authority(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    val nome: String,
) : GrantedAuthority {

    override fun getAuthority(): String {
        return nome
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}