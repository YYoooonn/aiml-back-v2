package org.aiml.api.dto

data class SignupResponse(
  val success: Boolean,
  val message: String? = null
)

data class LoginResponse(
  val accessToken: String,
  val refreshToken: String
)

data class ReissueResponse(
  val accessToken: String,
  val refreshToken: String? = null
)
