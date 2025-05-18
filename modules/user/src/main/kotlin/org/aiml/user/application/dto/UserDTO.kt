package org.aiml.user.application.dto

import java.time.LocalDateTime
import java.util.*

data class UserDTO(
  val id: UUID,
  val username: String,
  val password: String,
  val email: String,
  val firstName: String? = null,
  val lastName: String? = null,
  val bio: String? = null,
  val imageUrl: String? = null,
  val createdAt: LocalDateTime = LocalDateTime.now(),
  val updatedAt: LocalDateTime = LocalDateTime.now()
) {
  companion object {
    fun from(core: UserCoreDTO, profile: UserProfileDTO): UserDTO = UserDTO(
      id = core.id,
      username = core.username,
      password = core.password,
      email = core.email,
      firstName = profile.firstName,
      lastName = profile.lastName,
      bio = profile.bio,
      imageUrl = profile.imageUrl,
      createdAt = core.createdAt,
      updatedAt = profile.updatedAt
    )
  }
}

fun UserDTO.toUserCore(): UserCoreDTO = UserCoreDTO(
  id = this.id,
  username = this.username,
  password = this.password,
  email = this.email,
)

fun UserDTO.toUserProfile(): UserProfileDTO = UserProfileDTO(
  id = this.id, // profile id와 user id 통일?
  userId = this.id,
  firstName = this.firstName,
  lastName = this.lastName,
  bio = this.bio,
  imageUrl = this.imageUrl,
)
