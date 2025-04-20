package org.aiml.user.domain.service.profile

import org.aiml.user.domain.model.User
import org.aiml.user.domain.port.outbound.UserProfilePersistencePort
import org.springframework.stereotype.Service
import java.util.UUID


@Service
class GetUserProfileService(
  private val userProfilePersistencePort: UserProfilePersistencePort
) {
  fun getUserProfileById(id: UUID): User {
    TODO()
  }
}
