package org.aiml.api.user.dto

import org.aiml.user.domain.model.User
import java.time.LocalDateTime
import java.util.UUID

data class UserResponse(
  val id: UUID,
  val email: String,
  val username: String,
  val createdAt: LocalDateTime
)

fun User.toResponse(): UserResponse {
  return UserResponse(
    id = this.id,
    email = this.email,
    username = this.username,
    createdAt = this.createdAt
  )
}
