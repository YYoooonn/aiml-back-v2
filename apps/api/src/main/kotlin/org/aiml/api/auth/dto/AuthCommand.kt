package org.aiml.api.auth.dto

import org.aiml.user.domain.service.user.CreateUserCommand

data class SignupCommand(
  val username: String,
  val password: String,
  val email: String
) {
  fun toCreateUserCommand(encrypted: String): CreateUserCommand {
    return CreateUserCommand(
      username = username,
      password = encrypted,
      email = email
    )
  }
}
