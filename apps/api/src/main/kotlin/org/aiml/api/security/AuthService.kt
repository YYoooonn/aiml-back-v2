package org.aiml.api.security

import org.aiml.api.dto.LoginRequest
import org.aiml.api.dto.LoginResponse
import org.aiml.api.dto.ReissueRequest
import org.aiml.api.dto.ReissueResponse
import org.aiml.api.exception.InvalidTokenException
import org.aiml.libs.common.security.jwt.JwtTokenProvider
import org.aiml.user.domain.exception.UserNotFoundException
import org.aiml.user.application.UserServiceFacade
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthService(
  private val userServiceFacade: UserServiceFacade,
  private val passwordEncoder: PasswordEncoder,
  private val jwtTokenProvider: JwtTokenProvider
) {
//    fun signup(command: SignupCommand): Result<User> {
//        // user service 에서 encoding
//        // val encryptedPassword = passwordEncoder.encode(command.password)
//        val user = userUseCase.register(command.toRegisterCommand())
//        return user
//    }

  fun login(request: LoginRequest): LoginResponse {
    val result = userServiceFacade.getUserCore(request.username)
    if (result.isFailure) {
      throw UserNotFoundException("User ${request.username} not found")
    }

    val user = result.getOrThrow()
    if (!passwordEncoder.matches(request.password, user.encryptedPassword)) {
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
      val result = userServiceFacade.getUserCore(userId).getOrThrow()
      val aToken = jwtTokenProvider.generateAccessToken(userId, result.username)
      val rToken = jwtTokenProvider.generateRefreshToken(userId, result.username)
      return ReissueResponse(aToken, rToken)
    }
  }
}
