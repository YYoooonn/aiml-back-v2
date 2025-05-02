package org.aiml.api.features.auth.infra

import org.aiml.api.features.auth.repository.TokenStore
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.UUID
import java.time.Duration

@Component
class RedisTokenStore(
  private val redisTemplate: RedisTemplate<String, String>
) : TokenStore {
  override fun saveRefreshToken(userId: UUID, refreshToken: String, ttl: Duration) {
    val key = "refresh:user:$userId"
    redisTemplate.opsForValue().set(key, refreshToken, ttl)
  }

  override fun getRefreshToken(userId: UUID): String? {
    return redisTemplate.opsForValue().get("refresh:user:$userId")
  }

  override fun deleteRefreshToken(userId: UUID) {
    redisTemplate.delete("refresh:user:$userId")
  }

  override fun isAccessTokenBlacklisted(token: String): Boolean {
    return redisTemplate.hasKey("blacklist:token:$token")
  }

  override fun blacklistAccessToken(token: String, ttl: Duration) {
    redisTemplate.opsForValue().set("blacklist:token:$token", "true", ttl)
  }
}
