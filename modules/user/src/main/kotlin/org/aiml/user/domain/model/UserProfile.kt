package org.aiml.user.domain.model

import java.time.LocalDateTime
import java.util.UUID

data class UserProfile(
  val id: UUID,
  val userId: UUID,
  val firstName: String? = null,
  val lastName: String? = null,
  val bio: String? = null,
  val imageUrl: String? = null,
  val createdAt: LocalDateTime = LocalDateTime.now(),
  val updatedAt: LocalDateTime = LocalDateTime.now()
)
