package com.converter.api.service

import com.converter.api.model.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenService(
    @Value("\${jwt.expiration}") private val expiration: String,
    @Value("\${jwt.secret}") private val secret: String
) {

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

    fun isValidToken(token: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getUserId(token: String?): Long {
        val claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
        return claims.subject.toLong()
    }
}