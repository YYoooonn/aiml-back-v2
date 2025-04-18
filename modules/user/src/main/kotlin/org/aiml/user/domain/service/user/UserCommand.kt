package org.aiml.user.domain.service.user

import org.aiml.user.domain.model.User
import java.util.UUID

data class CreateUserCommand(
  val email: String,
  val username: String,
  val password: String,
  val firstName: String? = null,
  val lastName: String? = null,
) {
  fun toUser(): User {
    return User(
      username = username,
      email = email,
      encryptedPassword = password,
    )
  }
}

data class UpdateUserCommand(
  val username: String,
  val email: String,
  val firstName: String? = null,
  val lastName: String? = null,
) {

}
