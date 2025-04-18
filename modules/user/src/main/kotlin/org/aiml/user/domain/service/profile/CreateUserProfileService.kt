package org.aiml.user.domain.service.profile

import org.aiml.user.domain.model.UserProfile
import org.aiml.user.domain.port.output.UserProfilePersistencePort
import org.springframework.stereotype.Service

@Service
class CreateUserProfileService(
  private val userProfilePersistencePort: UserProfilePersistencePort
) {
  fun create(command: CreateUserProfileCommand): UserProfile {
    TODO()
  }
}
