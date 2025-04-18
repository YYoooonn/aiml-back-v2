package org.aiml.user.domain.service

import org.aiml.user.domain.exception.UserAlreadyExistsException
import org.aiml.user.domain.exception.UserNotFoundException
import org.aiml.user.domain.model.User
import org.aiml.user.domain.port.output.UserPersistencePort
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserDomainFacade(
  private val userPersistencePort: UserPersistencePort
) {

  fun checkUserExists(username: String): Boolean {
    return userPersistencePort.existsByUsername(username)
  }

  fun assertUserDoesNotExist(username: String) {
    if (checkUserExists(username)) {
      throw UserAlreadyExistsException("User $username already exists")
    }
  }

  fun getUserOrThrow(username: String): User {
    return userPersistencePort.findByUsername(username) ?: throw UserNotFoundException(username)
  }

  fun getUserOrThrow(id: UUID): User {
    return userPersistencePort.findById(id) ?: throw UserNotFoundException(id.toString())
  }
}
