package org.aiml.user.domain.service.user

import org.aiml.user.domain.model.User
import org.aiml.user.domain.port.output.UserPersistencePort
import org.aiml.user.domain.service.UserDomainFacade
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CreateUserService(
  private val userPersistencePort: UserPersistencePort,
  private val userDomainFacade: UserDomainFacade,
  private val passwordEncoder: PasswordEncoder
) {
  fun create(command: CreateUserCommand): Result<User> {
    val newUser = command.toUser()
    return runCatching {
      userDomainFacade.assertUserDoesNotExist(newUser.username)
      userPersistencePort.save(newUser)
    }
  }

  fun createAdmin(command: CreateUserCommand): Result<User> {
    TODO()
  }
}
