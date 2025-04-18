package org.aiml.api.auth.dto


data class SignupRequest(
  val email: String,
  val password: String,
  val username: String
) {
  fun toCommand(): SignupCommand {
    return SignupCommand(
      username = username,
      password = password,
      email = email
    )
  }
}

data class LoginRequest(
  val username: String,
  val password: String
)

data class ReissueRequest(
  val refreshToken: String,
)
