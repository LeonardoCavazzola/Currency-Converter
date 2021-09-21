package com.converter.api.service

import com.converter.api.model.User
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenService {

    @Value("\${jwt.expiration}")
    private lateinit var expiration: String

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    fun generateToken(authentication: Authentication): String {

        val (id, email) = authentication.principal as User
        val today = Date()
        val expirationDate = Date(today.time + expiration.toLong())
        val user: MutableMap<String, Any> = HashMap()

        user["email"] = email

        return Jwts.builder()
            .setClaims(user)
            .setIssuer("API")
            .setSubject(id.toString())
            .setIssuedAt(today)
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }

    fun isValidToken(token: String?) = try {
        Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
        true
    } catch (e: Exception) {
        false
    }

    fun getUserId(token: String?): Long = Jwts.parser()
        .setSigningKey(secret)
        .parseClaimsJws(token)
        .body.let {
            it.subject.toLong()
        }
}