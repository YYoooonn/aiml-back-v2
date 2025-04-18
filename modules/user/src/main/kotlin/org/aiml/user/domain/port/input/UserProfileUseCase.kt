package org.aiml.user.domain.port.input

import org.aiml.user.domain.model.User
import org.aiml.user.domain.model.UserProfile
import org.aiml.user.domain.service.profile.*
import java.util.UUID

interface UserProfileUseCase {
  fun getUserProfile(userId: UUID): UserProfile?
  fun createUserProfile(command: CreateUserProfileCommand): UserProfile
  fun updateUserProfile(command: UpdateUserProfileCommand): UserProfile?
}
