package org.aiml.api.mapper

import org.aiml.api.dto.RevalidateResponse
import org.aiml.api.dto.UpdateResponse
import org.aiml.api.dto.UserBaseResponse
import org.aiml.api.dto.UserProfileResponse
import org.aiml.user.application.UserDTO
import org.springframework.stereotype.Component

@Component
class UserMapper {
  fun toUserResponse(user: UserDTO): UserBaseResponse {
    return UserBaseResponse(
      email = user.email,
      username = user.username,
      createdAt = user.createdAt,
      updatedAt = user.updatedAt,
    )
  }

  fun toUserProfileResponse(user: UserDTO): UserProfileResponse = UserProfileResponse(
    base = toUserResponse(user),
    firstName = user.firstName,
    lastName = user.lastName,
    bio = user.bio,
    imageUrl = user.imageUrl
  )

  fun toRevalidateResponse(user: UserDTO): RevalidateResponse = RevalidateResponse(
    data = toUserResponse(user)
  )

  fun toUpdateResponse(user: UserDTO): UpdateResponse = UpdateResponse(
    data = toUserProfileResponse(user)
  )

}
