package org.aiml.user.application.dto

import org.aiml.user.domain.model.UserProfile
import java.time.LocalDateTime
import java.util.*

data class UserProfileDTO(
  val id: UUID = UUID.randomUUID(),
  val userId: UUID,
  val firstName: String? = null,
  val lastName: String? = null,
  val bio: String? = null,
  var imageUrl: String? = null,

  val createdAt: LocalDateTime = LocalDateTime.now(),
  val updatedAt: LocalDateTime = LocalDateTime.now()
) {
  companion object {
    fun from(domain: UserProfile): UserProfileDTO = UserProfileDTO(
      id = domain.id,
      firstName = domain.firstName,
      lastName = domain.lastName,
      bio = domain.bio,
      imageUrl = domain.imageUrl,
      userId = domain.userId,
      createdAt = domain.createdAt,
      updatedAt = domain.updatedAt
    )
  }

  fun toDomain() = UserProfile(
    userId = userId,
    firstName = firstName,
    lastName = lastName,
    bio = bio,
    imageUrl = imageUrl,
  )

  fun update(dto: UserProfileDTO): UserProfileDTO = this.copy(
    firstName = dto.firstName,
    lastName = dto.lastName,
    bio = dto.bio,
    imageUrl = dto.imageUrl
  )
}
