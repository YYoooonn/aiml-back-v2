package org.aiml.user.domain

import org.aiml.user.domain.exception.UserNotFoundException
import org.aiml.user.domain.port.output.UserPersistencePort
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.UserDetailsService
import java.util.UUID

@Service
class CustomUserDetailsService(
  private val userRepository: UserPersistencePort
) : UserDetailsService {

  override fun loadUserByUsername(username: String): UserDetails {
    val user = userRepository.findByUsername(username)
      ?: throw UserNotFoundException("User not found")

    return org.springframework.security.core.userdetails.User
      .withUsername(user.username)
      .password(user.encryptedPassword)
      .authorities("ROLE_USER") // 또는 user.roles.map { it.toAuthority() }
      .build()
  }
}
