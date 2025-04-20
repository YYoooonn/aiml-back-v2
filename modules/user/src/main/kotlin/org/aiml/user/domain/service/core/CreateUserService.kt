package org.aiml.user.domain.service.core

import org.aiml.user.domain.command.CreateUserCoreCommand
import org.aiml.user.domain.model.User
import org.aiml.user.domain.port.outbound.UserCorePersistencePort
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CreateUserService(
  private val userCorePersistencePort: UserCorePersistencePort,
  private val passwordEncoder: PasswordEncoder
) {
  fun create(command: CreateUserCoreCommand): User? {
    val encryptedPassword = passwordEncoder.encode(command.password)
    val newUser = User.from(command, encryptedPassword)
    return userCorePersistencePort.save(newUser)
  }

  fun createAdmin(command: CreateUserCoreCommand): User? {
    TODO()
  }
}
