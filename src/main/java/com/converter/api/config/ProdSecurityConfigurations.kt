package com.converter.api.config

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import com.converter.api.service.AuthenticationService
import com.converter.api.repository.UserRepository
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.http.HttpMethod
import org.springframework.security.config.http.SessionCreationPolicy
import com.converter.api.service.TokenService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.config.annotation.web.builders.WebSecurity

@Profile("prod")
@EnableWebSecurity
@Configuration
open class ProdSecurityConfigurations(
    private val authenticationService: AuthenticationService,
    private val tokenService: TokenService,
    private val userRepository: UserRepository
) : WebSecurityConfigurerAdapter() {

    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(authenticationService)
            .passwordEncoder(BCryptPasswordEncoder())
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers(HttpMethod.POST, "/auth").permitAll()
            .antMatchers(HttpMethod.POST, "/users").permitAll()
            .anyRequest().authenticated()
            .and()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(
                AuthenticationTokenFilter(tokenService, userRepository),
                UsernamePasswordAuthenticationFilter::class.java
            )
    }

    override fun configure(web: WebSecurity) {}
}