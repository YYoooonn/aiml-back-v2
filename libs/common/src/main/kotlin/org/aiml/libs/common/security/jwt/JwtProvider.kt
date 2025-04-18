package org.aiml.libs.common.security.jwt

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtProvider {
  @Value("\${security.jwt.secret-key}")
  private lateinit var secretKey: String

  private val key: Key by lazy {
    Keys.hmacShaKeyFor(secretKey.toByteArray())
  }

  fun generateToken(claims: Map<String, Any>, expirationMillis: Long): String {
    val now = Date()
    val expiryDate = Date(now.time + expirationMillis)

    return Jwts.builder()
      .setClaims(claims)
      .setIssuedAt(now)
      .setExpiration(expiryDate)
      .signWith(key)
      .compact()
  }

  fun isValidToken(token: String): Boolean {
    return try {
      getClaims(token)
      true
    } catch (ex: Exception) {
      false
    }
  }

  fun isExpired(token: String): Boolean {
    return try {
      val claims = getClaims(token)
      claims.expiration.before(Date())
    } catch (e: ExpiredJwtException) {
      true
    }
  }

  fun getClaims(token: String): Claims {
    return Jwts.parserBuilder()
      .setSigningKey(key)
      .build()
      .parseClaimsJws(token).body
  }
}
