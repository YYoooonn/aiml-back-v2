package org.aiml.user.infra.security

import org.aiml.user.exception.UserNotFoundException
import org.aiml.user.domain.port.outbound.UserCorePersistencePort
import org.springframework.context.annotation.Lazy
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.UserDetailsService

@Service
class CustomUserDetailsService(
  @Lazy private val userRepository: UserCorePersistencePort
) : UserDetailsService {

  override fun loadUserByUsername(username: String): UserDetails {
    val user = userRepository.findByUsername(username).getOrThrow()
      ?: throw UserNotFoundException("User not found")

    return CustomUserPrincipal(
      userId = user.id,
      username = user.username,
      password = user.encryptedPassword,
      authorities = listOf(SimpleGrantedAuthority("ROLE_USER"))
    )
  }
}
