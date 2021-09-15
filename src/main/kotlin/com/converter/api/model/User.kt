package com.converter.api.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @Column(nullable = false) val email: String,
    @Column(nullable = false) private val password: String,
    @ManyToMany(fetch = FetchType.EAGER) val authoritys: List<Authority> = mutableListOf()
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return authoritys
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}