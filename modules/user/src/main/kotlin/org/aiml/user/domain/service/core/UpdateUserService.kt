package org.aiml.user.domain.service.core

import org.aiml.user.domain.command.UpdateUserCoreCommand
import org.aiml.user.domain.model.User
import org.aiml.user.domain.port.outbound.UserCorePersistencePort
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UpdateUserService(
  private val userCorePersistencePort: UserCorePersistencePort,
  private val passwordEncoder: PasswordEncoder
) {
  fun update(userId: UUID, command: UpdateUserCoreCommand): User? {
    val old = userCorePersistencePort.findById(userId) ?: return null

    val encryptedPassword = command.password?.let { passwordEncoder.encode(it) }
    val new = old.update(command, encryptedPassword)
    return userCorePersistencePort.save(new)
  }
}
