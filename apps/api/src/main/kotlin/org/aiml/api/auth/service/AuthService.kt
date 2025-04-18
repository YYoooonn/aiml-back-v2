package org.aiml.api.auth.service

import org.aiml.api.auth.dto.*
import org.aiml.api.exception.InvalidTokenException
import org.aiml.libs.common.security.jwt.JwtTokenProvider
import org.aiml.user.domain.exception.UserNotFoundException
import org.aiml.user.domain.model.User
import org.aiml.user.domain.port.input.UserUseCase
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
  private val userUseCase: UserUseCase,
  private val passwordEncoder: PasswordEncoder,
  private val jwtTokenProvider: JwtTokenProvider
) {
  fun signup(command: SignupCommand): Result<User> {
    val encryptedPassword = passwordEncoder.encode(command.password)
    return userUseCase.createUser(command.toCreateUserCommand(encryptedPassword))
  }

  fun login(request: LoginRequest): LoginResponse {
    val result = userUseCase.getUserByUsername(request.username)
    if (result.isFailure) {
      throw UserNotFoundException("User ${request.username} not found")
    }

    val user = result.getOrThrow()
    if (!passwordEncoder.matches(request.password, user.encryptedPassword)) {
      throw IllegalArgumentException("Password not match")
    }
    val accessToken = jwtTokenProvider.generateAccessToken(user.username)
    val refreshToken = jwtTokenProvider.generateRefreshToken(user.username)

    return LoginResponse(accessToken, refreshToken)
  }

  fun reissue(refreshToken: String): String {
    if (!jwtTokenProvider.validateRefreshToken(refreshToken)) {
      throw InvalidTokenException("Refresh token is invalid")
    }

    val username = jwtTokenProvider.getUsernameFromToken(refreshToken)

    return jwtTokenProvider.generateAccessToken(username)
  }
}
