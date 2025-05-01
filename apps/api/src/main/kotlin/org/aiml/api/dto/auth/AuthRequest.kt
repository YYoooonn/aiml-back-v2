package org.aiml.api.dto.auth

data class LoginRequest(
  val username: String,
  val password: String
)

data class ReissueRequest(
  val refreshToken: String,
  val revalidate: Boolean? = false
)
