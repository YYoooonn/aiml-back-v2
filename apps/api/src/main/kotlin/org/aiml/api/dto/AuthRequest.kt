package org.aiml.api.dto

data class LoginRequest(
  val username: String,
  val password: String
)

data class ReissueRequest(
  val refreshToken: String,
  val revalidate: Boolean? = false
)
