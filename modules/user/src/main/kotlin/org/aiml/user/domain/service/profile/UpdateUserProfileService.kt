package org.aiml.user.domain.service.profile

import org.aiml.user.domain.model.User
import org.aiml.user.domain.port.output.UserPersistencePort
import org.springframework.stereotype.Service

@Service
class UpdateUserProfileService(
  private val userPersistencePort: UserPersistencePort
) {
  fun update(command: UpdateUserProfileCommand): User? {
    TODO()
  }
}
