package org.aiml.api.dto

import com.fasterxml.jackson.annotation.JsonUnwrapped
import java.time.LocalDateTime

data class UserBaseResponse(
  val username: String,
  val email: String,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime
)

data class UserProfileResponse(
  @JsonUnwrapped
  val base: UserBaseResponse,

  val firstName: String? = null,
  val lastName: String? = null,
  val bio: String? = null,
  val imageUrl: String? = null,
)

data class RevalidateResponse(
  val data: UserBaseResponse,
  val revalidate: Boolean = true
)

data class UpdateResponse(
  val data: UserProfileResponse,
  val revalidate: Boolean = false
)
