package org.aiml.user.domain.service.user

import jakarta.transaction.Transactional
import org.aiml.user.domain.model.User
import org.aiml.user.domain.port.output.UserPersistencePort
import org.aiml.user.domain.service.UserDomainFacade
import org.springframework.stereotype.Service
import java.util.UUID


@Transactional
@Service
class GetUserService(
  private val userPersistencePort: UserPersistencePort,
  private val userDomainFacade: UserDomainFacade
) {
  fun getUserById(id: UUID): Result<User> {
    TODO()
  }

  fun getAllUsers(): List<User> {
    return userPersistencePort.findAll()
  }

  fun getUserByUsername(username: String): Result<User> {
    return runCatching {
      userDomainFacade.getUserOrThrow(username)
    }
  }

  fun getUserByEmail(email: String): Result<User> {
    TODO()
  }

}
