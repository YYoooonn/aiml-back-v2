package org.aiml.user.domain.service.profile

import org.aiml.user.domain.command.CreateUserProfileCommand
import org.aiml.user.domain.model.UserProfile
import org.aiml.user.domain.port.outbound.UserProfilePersistencePort
import org.springframework.stereotype.Service

@Service
class CreateUserProfileService(
  private val userProfilePersistencePort: UserProfilePersistencePort
) {
  fun create(command: CreateUserProfileCommand): UserProfile {
    TODO()
  }
}
