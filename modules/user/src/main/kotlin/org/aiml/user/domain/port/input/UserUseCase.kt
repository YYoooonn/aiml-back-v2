package org.aiml.user.domain.port.input

import org.aiml.user.domain.model.User
import org.aiml.user.domain.service.user.*
import java.util.UUID

interface UserUseCase {
  fun getAllUsers(): Iterable<User>
  fun getUserById(id: UUID): Result<User>
  fun getUserByUsername(username: String): Result<User>
  fun updateUser(command: UpdateUserCommand): Result<User>
  fun createUser(command: CreateUserCommand): Result<User>
}
