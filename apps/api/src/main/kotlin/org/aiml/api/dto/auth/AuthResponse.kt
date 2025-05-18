package org.aiml.api.dto.auth

import org.aiml.api.features.auth.domain.AuthTokens

data class TokenResponse(
  val accessToken: String,
  val refreshToken: String? = null
) {
  companion object {
    fun from(authTokens: AuthTokens): TokenResponse {
      return TokenResponse(
        accessToken = authTokens.accessToken,
        refreshToken = authTokens.refreshToken
      )
    }
  }
}
