package org.aiml.user.domain.command

data class CreateUserCoreCommand(
  val email: String,
  val username: String,
  val password: String,
)

data class UpdateUserCoreCommand(
  val username: String? = null,
  val email: String? = null,
  val password: String? = null,
)
