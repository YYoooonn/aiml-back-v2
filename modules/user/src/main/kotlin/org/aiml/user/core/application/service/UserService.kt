package org.aiml.user.core.application.service

import org.aiml.user.core.application.port.incoming.UserUseCase
import org.aiml.user.core.application.port.outgoing.UserPersistencePort
import org.aiml.user.core.domain.model.User
import org.springframework.stereotype.Service

@Service
class UserService(
  private val port: UserPersistencePort
) : UserUseCase {
  override fun registerUser(
    username: String,
    password: String,
    firstname: String?,
    lastname: String?,
    email: String
  ): User {
    TODO("Not yet implemented")
  }

  override fun getUserById(id: Long): User? {
    TODO("Not yet implemented")
  }

  override fun getUserByEmail(email: String): User? {
    TODO("Not yet implemented")
  }

  override fun getUserByUsername(username: String): User? {
    TODO("Not yet implemented")
  }
}
