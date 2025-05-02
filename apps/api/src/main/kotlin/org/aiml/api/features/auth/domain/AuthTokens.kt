package org.aiml.api.features.auth.domain

data class AuthTokens(
  val accessToken: String,
  val refreshToken: String
)
