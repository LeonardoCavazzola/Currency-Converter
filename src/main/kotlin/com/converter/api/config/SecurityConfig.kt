package com.converter.api.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val userIdFilter: UserIdFilter
) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        // General
        http.csrf().disable()
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        // Authorizations
        http.authorizeRequests().anyRequest().authenticated()

        // Filters
        http.addFilterBefore(userIdFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}
