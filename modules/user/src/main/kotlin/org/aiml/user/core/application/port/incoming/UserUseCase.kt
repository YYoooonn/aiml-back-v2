package org.aiml.user.core.application.port.incoming

import org.aiml.user.core.domain.model.User

interface UserUseCase {
  fun registerUser(
    username: String,
    password: String,
    firstname: String?,
    lastname: String?,
    email: String,
  ): User

  fun getUserByUsername(username: String): User?
  fun getUserByEmail(email: String): User?
  fun getUserById(id: Long): User?
}
