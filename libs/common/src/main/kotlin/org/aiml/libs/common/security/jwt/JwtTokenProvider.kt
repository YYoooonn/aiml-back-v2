package org.aiml.libs.common.security.jwt

import org.springframework.stereotype.Component

@Component
class JwtTokenProvider(
  private val jwtProvider: JwtProvider
) {
  private val accessTokenValidity = 1000 * 60 * 30L  // 30분
  private val refreshTokenValidity = 1000 * 60 * 60 * 24 * 7L  // 7일

  fun generateAccessToken(id: String): String {
    return jwtProvider.generateToken(mapOf("sub" to id, "type" to "access"), accessTokenValidity)
  }

  fun generateRefreshToken(id: String): String {
    return jwtProvider.generateToken(mapOf("sub" to id, "type" to "refresh"), refreshTokenValidity)
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
    return jwtProvider.getClaims(token).subject
  }
}
