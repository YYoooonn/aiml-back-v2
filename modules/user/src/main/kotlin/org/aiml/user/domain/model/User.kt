package org.aiml.user.domain.model

import java.time.LocalDateTime
import java.util.UUID

data class User(
  val id: UUID = UUID.randomUUID(),
  val username: String,
  val encryptedPassword: String,
  val email: String,
  val role: Role? = Role.USER,
  val createdAt: LocalDateTime = LocalDateTime.now(),
) {
  enum class Role {
    ADMIN, USER
  }
}
