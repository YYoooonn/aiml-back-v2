package org.aiml.user.application.dto

import org.aiml.user.domain.model.User
import java.time.LocalDateTime
import java.util.*

data class UserCoreDTO(
  val id: UUID = UUID.randomUUID(),
  val username: String,
  val email: String,
  val password: String,
  val createdAt: LocalDateTime = LocalDateTime.now(),
  val updatedAt: LocalDateTime = LocalDateTime.now()
) {
  fun toDomain(encrypted: String): User {
    return User(
      id = id,
      username = username,
      email = email,
      encryptedPassword = encrypted,
    )
  }

  companion object {
    fun from(domain: User): UserCoreDTO = UserCoreDTO(
      id = domain.id,
      username = domain.username,
      email = domain.email,
      password = domain.encryptedPassword,
      createdAt = domain.createdAt,
      updatedAt = domain.updatedAt
    )
  }
}
