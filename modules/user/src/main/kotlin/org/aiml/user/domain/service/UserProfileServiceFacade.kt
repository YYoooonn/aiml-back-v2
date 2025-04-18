package org.aiml.user.domain.service

import org.aiml.user.domain.port.input.UserProfileUseCase
import org.aiml.user.domain.model.User
import org.aiml.user.domain.model.UserProfile
import org.aiml.user.domain.service.profile.*
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserProfileServiceFacade(
  private val getUserProfileService: GetUserProfileService,
  private val createUserProfileService: CreateUserProfileService,
  private val updateUserProfileService: UpdateUserProfileService,
) : UserProfileUseCase {

  override fun getUserProfile(userId: UUID): UserProfile? {
    TODO("Not yet implemented")
  }

  override fun createUserProfile(command: CreateUserProfileCommand): UserProfile {
    TODO("Not yet implemented")
  }

  override fun updateUserProfile(command: UpdateUserProfileCommand): UserProfile {
    TODO("Not yet implemented")
  }
}
