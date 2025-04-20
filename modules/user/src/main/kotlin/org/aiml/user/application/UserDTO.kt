package org.aiml.user.application

import org.aiml.user.domain.model.User
import org.aiml.user.domain.model.UserProfile
import java.time.LocalDateTime
import java.util.UUID

data class UserDTO(
  val id: UUID,
  val username: String,
  val email: String,
  val firstName: String? = null,
  val lastName: String? = null,
  val bio: String? = null,
  val imageUrl: String? = null,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime
) {
  companion object {
    fun from(user: User, profile: UserProfile): UserDTO = UserDTO(
      id = user.id,
      username = user.username,
      email = user.email,
      firstName = profile.firstName,
      lastName = profile.lastName,
      bio = profile.bio,
      imageUrl = profile.imageUrl,
      createdAt = user.createdAt,
      updatedAt = profile.updatedAt
    )
  }
}
