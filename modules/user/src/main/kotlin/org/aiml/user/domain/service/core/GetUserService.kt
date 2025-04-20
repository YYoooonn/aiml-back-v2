package org.aiml.user.domain.service.core

import jakarta.transaction.Transactional
import org.aiml.user.domain.model.User
import org.aiml.user.domain.port.outbound.UserCorePersistencePort
import org.springframework.stereotype.Service
import java.util.UUID


@Transactional
@Service
class GetUserService(
  private val userCorePersistencePort: UserCorePersistencePort,
) {
  fun getUserById(id: UUID): User? {
    return userCorePersistencePort.findById(id)
  }

  fun getAllUsers(): List<User> {
    return userCorePersistencePort.findAll()
  }

  fun getUserByUsername(username: String): User? {
    return userCorePersistencePort.findByUsername(username)
  }

  fun getUserByEmail(email: String): Result<User> {
    TODO()
  }

}
