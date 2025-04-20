package org.aiml.user.domain.service.core

import org.aiml.user.domain.port.outbound.UserCorePersistencePort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DeleteUserService(
  private val userCorePersistencePort: UserCorePersistencePort,
) {
  fun delete(userId: UUID) {
    userCorePersistencePort.delete(userId)
  }
}
