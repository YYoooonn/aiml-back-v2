package org.aiml.api.features.auth.service

import org.aiml.api.dto.auth.*
import org.aiml.api.features.auth.domain.AuthTokens
import org.aiml.api.features.auth.repository.TokenStore
import org.aiml.api.security.exception.InvalidTokenException
import org.aiml.api.security.exception.TokenExpiredException
import org.aiml.libs.common.security.jwt.JwtTokenProvider
import org.aiml.user.application.UserAuthenticator
import org.springframework.stereotype.Service
import java.util.*
import java.time.Duration

@Service
class AuthService(
  private val userAuthenticator: UserAuthenticator,
  private val jwtTokenProvider: JwtTokenProvider,
  private val tokenStore: TokenStore,
) {

  private val accessTokenValidity = Duration.ofMinutes(30) // 30분
  private val refreshTokenValidity = Duration.ofDays(7) // 7일

  fun login(username: String, password: String): AuthTokens {
    val user = userAuthenticator.authenticate(username, password)

    val accessToken = jwtTokenProvider.generateAccessToken(user.id, user.username, accessTokenValidity)
    val refreshToken = jwtTokenProvider.generateRefreshToken(user.id, user.username, refreshTokenValidity)

    tokenStore.saveRefreshToken(user.id, refreshToken, refreshTokenValidity)

    return AuthTokens(accessToken, refreshToken)
  }

  fun logout(userId: UUID, accessToken: String) {
    tokenStore.deleteRefreshToken(userId)
    tokenStore.blacklistAccessToken(accessToken, accessTokenValidity)
  }

  fun reissue(refreshToken: String): AuthTokens {
    val username = jwtTokenProvider.getUsernameFromToken(refreshToken)
    val userId = jwtTokenProvider.getUserIdFromToken(refreshToken)

    val storedToken = tokenStore.getRefreshToken(userId)
      ?: throw InvalidTokenException("Invalid refresh token")

    // Redis에 저장된 refreshToken과 비교
    if (storedToken != refreshToken) {
      throw InvalidTokenException("Refresh token does not match")
    }

    // refresh token valid 여부 비교
    if (!jwtTokenProvider.validateRefreshToken(refreshToken)) {
      throw TokenExpiredException("refresh token invalid or expired")
    }


    val aToken = jwtTokenProvider.generateAccessToken(userId, username)
    val rToken = jwtTokenProvider.generateRefreshToken(userId, username)

    tokenStore.saveRefreshToken(userId, rToken, refreshTokenValidity)

    return AuthTokens(aToken, rToken)
  }
}
