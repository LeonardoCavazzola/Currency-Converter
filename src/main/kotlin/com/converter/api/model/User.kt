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

    override fun getAuthorities(): Collection<GrantedAuthority?> = authoritys

    override fun getPassword() =  password

    override fun getUsername() = email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked()=  true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true

    companion object {
        // Geralmente o Padrão é que fique dessa forma
        private const val SERIAL_VERSION_UID = 1L
    }
}