package org.aiml.libs.infra.redis

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig(
  @Value("\${spring.data.redis.host}") private val host: String,
  @Value("\${spring.data.redis.port}") private val port: Int,
  @Value("\${spring.data.redis.password}") private val password: String,
) {
  @Bean
  fun redisConnectionFactory(): RedisConnectionFactory {
    val config = RedisStandaloneConfiguration(host, port)
    config.password = RedisPassword.of(password)
    return LettuceConnectionFactory(config)
  }

  @Bean
  fun redisTemplate(): RedisTemplate<String, String> {
    val template = RedisTemplate<String, String>()
    template.connectionFactory = redisConnectionFactory()
    template.keySerializer = StringRedisSerializer()
    template.valueSerializer = StringRedisSerializer()
    return template
  }
}
