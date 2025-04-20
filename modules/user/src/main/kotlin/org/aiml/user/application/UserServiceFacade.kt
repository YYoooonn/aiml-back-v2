package org.aiml.user.application

import org.aiml.user.infra.security.CustomUserPrincipal
import org.aiml.user.domain.model.User
import java.util.UUID

interface UserServiceFacade {

  fun register(command: RegisterCommand): Result<UserDTO>
  fun getUserCore(username: String): Result<User>
  fun getUserCore(userId: UUID): Result<User>
  fun getProfile(username: String): Result<UserDTO>

  // with authentication
  fun delete(principal: CustomUserPrincipal): Result<Unit>
  fun getUser(principal: CustomUserPrincipal): Result<UserDTO>
  fun getProfile(principal: CustomUserPrincipal): Result<UserDTO>

  // core update, needs to revalidate token
  fun updateUser(principal: CustomUserPrincipal, command: UpdateCommand): Result<UserDTO>

  // profile update, no need to revalidate token
  fun updateUserProfile(principal: CustomUserPrincipal, command: UpdateCommand): Result<UserDTO>

  // for admin
  fun getUsers(): Result<List<User>>
  fun getProfiles(): Result<List<UserDTO>>
}
