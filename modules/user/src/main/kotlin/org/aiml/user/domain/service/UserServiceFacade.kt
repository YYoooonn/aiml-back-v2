package org.aiml.user.domain.service

import jakarta.transaction.Transactional
import org.aiml.user.domain.port.input.UserUseCase
import org.aiml.user.domain.model.User
import org.aiml.user.domain.service.user.*
import org.springframework.stereotype.Service
import java.util.*

@Transactional
@Service
class UserServiceFacade(
  private val getUserService: GetUserService,
  private val createUserService: CreateUserService,
  private val updateUserService: UpdateUserService,
) : UserUseCase {

  override fun getUserById(id: UUID): Result<User> {
    return getUserService.getUserById(id)
  }

  override fun getAllUsers(): Iterable<User> {
    return getUserService.getAllUsers()
  }

  override fun getUserByUsername(username: String): Result<User> {
    return getUserService.getUserByUsername(username)
  }

  override fun createUser(command: CreateUserCommand): Result<User> {
    return createUserService.create(command)

  }

  override fun updateUser(command: UpdateUserCommand): Result<User> {
    return runCatching { updateUserService.update(command) }
  }
}
