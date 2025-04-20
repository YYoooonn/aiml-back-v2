package org.aiml.user.domain.service.profile

import org.aiml.user.domain.command.CreateUserProfileCommand
import org.aiml.user.domain.command.UpdateUserProfileCommand
import org.aiml.user.infra.security.CustomUserPrincipal
import org.aiml.user.domain.exception.UserNotFoundException
import org.aiml.user.domain.port.inbound.UserProfileUseCase
import org.aiml.user.domain.model.User
import org.aiml.user.domain.model.UserProfile
import org.aiml.user.domain.port.outbound.UserProfilePersistencePort
import org.springframework.stereotype.Service

// TODO 복잡해 지면, service/profile 에서 나누기

@Service
class UserProfileService(
  private val userProfilePersistencePort: UserProfilePersistencePort
) : UserProfileUseCase {

  override fun getUserProfile(username: String): UserProfile {
    return userProfilePersistencePort.findByUsername(username)
      ?: throw UserNotFoundException(username)
  }

  override fun createUserProfile(command: CreateUserProfileCommand, user: User): UserProfile {
    return userProfilePersistencePort.save(UserProfile.from(command), user)
      ?: throw InternalError("error while saving user profile")
  }

  override fun getUserProfile(principal: CustomUserPrincipal): UserProfile {
    return userProfilePersistencePort.findByUserId(principal.userId)
      ?: throw UserNotFoundException(principal.username)
  }

  override fun updateUserProfile(
    principal: CustomUserPrincipal,
    command: UpdateUserProfileCommand,
    user: User
  ): UserProfile {
    val existing = getUserProfile(principal)
    return userProfilePersistencePort.save(existing.update(command), user)
      ?: throw RuntimeException("error while saving user profile")

  }

  override fun deleteUserProfile(principal: CustomUserPrincipal) {
    return userProfilePersistencePort.delete(principal.userId)

  }

  override fun getAllUserProfile(principal: CustomUserPrincipal): UserProfile {
    TODO("Not yet implemented")
  }

}
