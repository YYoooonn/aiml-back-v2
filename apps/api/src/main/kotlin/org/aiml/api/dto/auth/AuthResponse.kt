package org.aiml.api.dto.auth

data class LoginResponse(
  val accessToken: String,
  val refreshToken: String
)

data class ReissueResponse(
  val accessToken: String,
  val refreshToken: String? = null
)
