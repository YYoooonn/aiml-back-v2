package org.aiml.api.security

import org.aiml.api.dto.auth.LoginRequest
import org.aiml.api.dto.auth.LoginResponse
import org.aiml.api.dto.auth.ReissueRequest
import org.aiml.api.dto.auth.ReissueResponse
import org.aiml.api.security.exception.InvalidTokenException
import org.aiml.libs.common.security.jwt.JwtTokenProvider
import org.aiml.user.exception.UserNotFoundException
import org.aiml.user.domain.port.inbound.UserCoreQueryService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthService(
  private val userCoreQueryService: UserCoreQueryService,
  private val passwordEncoder: PasswordEncoder,
  private val jwtTokenProvider: JwtTokenProvider
) {

  fun login(request: LoginRequest): LoginResponse {
    val user = userCoreQueryService.findByUsername(request.username)
      ?: throw UserNotFoundException("User ${request.username} not found")

    if (!passwordEncoder.matches(request.password, user.password)) {
      throw IllegalArgumentException("Password not match")
    }
    val accessToken = jwtTokenProvider.generateAccessToken(user.id, user.username)
    val refreshToken = jwtTokenProvider.generateRefreshToken(user.id, user.username)

    return LoginResponse(accessToken, refreshToken)
  }

  fun reissue(request: ReissueRequest): ReissueResponse {
    val refreshToken = request.refreshToken
    if (!jwtTokenProvider.validateRefreshToken(refreshToken)) {
      throw InvalidTokenException("Refresh token is invalid, please login again")
    }

    if (request.revalidate == false) {
      val username = jwtTokenProvider.getUsernameFromToken(refreshToken)
      val userId = jwtTokenProvider.getUserIdFromToken(refreshToken)
      return ReissueResponse(
        accessToken = jwtTokenProvider.generateAccessToken(
          UUID.fromString(userId),
          username
        )
      )
    } else {
      val userId = UUID.fromString(jwtTokenProvider.getUserIdFromToken(refreshToken))
      val result = userCoreQueryService.findById(userId)
      val aToken = jwtTokenProvider.generateAccessToken(userId, result.username)
      val rToken = jwtTokenProvider.generateRefreshToken(userId, result.username)
      return ReissueResponse(aToken, rToken)
    }
  }
}
