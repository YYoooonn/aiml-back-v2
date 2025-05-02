package org.aiml.api.features.auth.repository

import java.util.*
import java.time.Duration

interface TokenStore {
  fun saveRefreshToken(userId: UUID, refreshToken: String, ttl: Duration)
  fun getRefreshToken(userId: UUID): String?
  fun deleteRefreshToken(userId: UUID)
  fun isAccessTokenBlacklisted(token: String): Boolean
  fun blacklistAccessToken(token: String, ttl: Duration)
}
