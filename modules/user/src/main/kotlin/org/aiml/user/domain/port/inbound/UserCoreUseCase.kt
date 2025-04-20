package org.aiml.user.domain.port.inbound

import org.aiml.user.infra.security.CustomUserPrincipal
import org.aiml.user.domain.model.User
import org.aiml.user.domain.command.CreateUserCoreCommand
import org.aiml.user.domain.command.UpdateUserCoreCommand
import java.util.*

interface UserCoreUseCase {
  fun getUser(principal: CustomUserPrincipal): User
  fun updateUser(principal: CustomUserPrincipal, command: UpdateUserCoreCommand): User
  fun createUser(command: CreateUserCoreCommand): User
  fun deleteUser(principal: CustomUserPrincipal)

  // for admin
  fun getAllUsers(): Iterable<User>

  fun getUserById(id: UUID): User
  fun getUserByUsername(username: String): User
}
