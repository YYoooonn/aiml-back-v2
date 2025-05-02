package org.aiml.user.application

import org.aiml.user.application.dto.UserCoreDTO
import org.aiml.user.domain.port.inbound.UserCoreQueryService
import org.aiml.user.exception.InvalidCredentialsException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserAuthenticator(
  private val userCoreQueryService: UserCoreQueryService,
  private val passwordEncoder: PasswordEncoder
) {
  fun authenticate(username: String, rawPassword: String): UserCoreDTO {
    val user = userCoreQueryService.findByUsername(username)
      ?: throw InvalidCredentialsException("Invalid username")

    if (!passwordEncoder.matches(rawPassword, user.password)) {
      throw InvalidCredentialsException("Invalid password")
    }

    return user
  }

}
