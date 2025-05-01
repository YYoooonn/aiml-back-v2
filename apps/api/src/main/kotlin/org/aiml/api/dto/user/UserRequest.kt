package org.aiml.api.dto.user

import org.aiml.api.exception.RequestInvalidException
import org.aiml.user.application.dto.UserCoreDTO
import org.aiml.user.application.dto.UserDTO
import org.aiml.user.application.dto.UserProfileDTO
import java.util.UUID


data class UserRequest(
  val username: String,
  val password: String,
  val email: String,
  val firstName: String? = null,
  val lastName: String? = null,
  val bio: String? = null,
  val imageUrl: String? = null,
)

fun UserRequest.toDTO(id: UUID? = null): UserDTO {
  return UserDTO(
    id = id ?: UUID.randomUUID(),
    username = username,
    password = password,
    email = email,
    firstName = firstName,
    lastName = lastName,
    bio = bio,
    imageUrl = imageUrl
  )
}

data class UserCoreRequest(
  val username: String,
  val password: String,
  val email: String? = null,
)

fun UserCoreRequest.toDTO(id: UUID?): UserCoreDTO {
  if (email == null) throw RequestInvalidException("user email is required")
  return UserCoreDTO(
    id = id ?: UUID.randomUUID(),
    username = username,
    password = password,
    email = email
  )
}

data class UserProfileRequest(
  val firstName: String? = null,
  val lastName: String? = null,
  val bio: String? = null,
  val imageUrl: String? = null,
)

fun UserProfileRequest.toDTO(userId: UUID): UserProfileDTO {
  return UserProfileDTO(
    userId = userId,
    firstName = firstName,
    lastName = lastName,
    bio = bio,
    imageUrl = imageUrl
  )
}

