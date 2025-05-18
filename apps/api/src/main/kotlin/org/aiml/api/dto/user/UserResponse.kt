package org.aiml.api.dto.user

import org.aiml.user.application.dto.UserCoreDTO
import org.aiml.user.application.dto.UserDTO
import org.aiml.user.application.dto.UserProfileDTO
import java.time.LocalDateTime

data class UserResponse(
  val username: String,
  val email: String,
  val firstName: String,
  val lastName: String,
  val bio: String,
  val imageUrl: String,

  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime
) {
  companion object {
    fun from(dto: UserDTO): UserResponse {
      return UserResponse(
        username = dto.username,
        email = dto.email,
        firstName = dto.firstName ?: "",
        lastName = dto.lastName ?: "",
        bio = dto.bio ?: "",
        imageUrl = dto.imageUrl ?: "",

        createdAt = dto.createdAt,
        updatedAt = dto.updatedAt
      )
    }
  }
}

data class UserCoreResponse(
  val username: String,
  val email: String,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime
) {
  companion object {
    fun from(dto: UserCoreDTO): UserCoreResponse = UserCoreResponse(
      username = dto.username,
      email = dto.email,
      createdAt = dto.createdAt,
      updatedAt = dto.updatedAt
    )
  }
}

data class UserProfileResponse(
  val firstName: String,
  val lastName: String,
  val bio: String,
  val imageUrl: String,
) {
  companion object {
    fun from(dto: UserProfileDTO): UserProfileResponse = UserProfileResponse(
      firstName = dto.firstName ?: "",
      lastName = dto.lastName ?: "",
      bio = dto.bio ?: "",
      imageUrl = dto.imageUrl ?: "",
    )
  }
}
