package org.aiml.api.user.dto

import org.aiml.user.domain.service.user.CreateUserCommand

data class UserRequest(
  val username: String,
  val password: String,
  val email: String,
) {
  fun toCommand(): CreateUserCommand {
    return CreateUserCommand(username, password, email)
  }
}
