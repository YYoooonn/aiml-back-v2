package org.aiml.user.domain.port.inbound

import org.aiml.user.infra.security.CustomUserPrincipal
import org.aiml.user.domain.model.User
import org.aiml.user.domain.model.UserProfile
import org.aiml.user.domain.command.CreateUserProfileCommand
import org.aiml.user.domain.command.UpdateUserProfileCommand

interface UserProfileUseCase {

  fun getUserProfile(username: String): UserProfile

  // auth
  fun getUserProfile(principal: CustomUserPrincipal): UserProfile
  fun createUserProfile(command: CreateUserProfileCommand, user: User): UserProfile
  fun updateUserProfile(
    principal: CustomUserPrincipal,
    command: UpdateUserProfileCommand,
    user: User
  ): UserProfile

  fun deleteUserProfile(principal: CustomUserPrincipal)

  // for admin
  fun getAllUserProfile(principal: CustomUserPrincipal): UserProfile
//  fun getUserProfile(userId: UUID): Result<UserProfile>
//  fun getUserProfile(username: String): Result<UserProfile>
}
