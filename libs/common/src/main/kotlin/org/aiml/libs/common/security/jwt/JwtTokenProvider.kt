package org.aiml.libs.common.security.jwt

import org.springframework.stereotype.Component
import java.util.*
import java.time.Duration

@Component
class JwtTokenProvider(
  private val jwtProvider: JwtProvider
) {
  private val accessTokenValidity = Duration.ofMinutes(30)  // 30분
  private val refreshTokenValidity = Duration.ofDays(7)  // 7일

  fun generateAccessToken(id: UUID, username: String, ttl: Duration? = null): String {
    val time = ttl ?: accessTokenValidity
    return jwtProvider.generateToken(
      mapOf("sub" to id, "type" to "access", "username" to username),
      time.toMillis()
    )
  }

  fun generateRefreshToken(id: UUID, username: String, ttl: Duration? = null): String {
    val time = ttl ?: refreshTokenValidity
    return jwtProvider.generateToken(
      mapOf("sub" to id, "type" to "refresh", "username" to username),
      time.toMillis()
    )
  }

  fun validateAccessToken(token: String): Boolean {
    val claims = jwtProvider.getClaims(token)
    return jwtProvider.isValidToken(token) && claims["type"] == "access"
  }

  fun validateRefreshToken(token: String): Boolean {
    val claims = jwtProvider.getClaims(token)
    return jwtProvider.isValidToken(token) && claims["type"] == "refresh"
  }

  fun getUsernameFromToken(token: String): String {
    return jwtProvider.getClaims(token)["username"] as String
  }

  fun getUserIdFromToken(token: String): UUID {
    return UUID.fromString(jwtProvider.getClaims(token).subject)
  }

  fun getUserClaimsFromToken(token: String): Pair<String, String> {
    val claims = jwtProvider.getClaims(token)
    return claims.subject to claims["username"] as String
  }
}
