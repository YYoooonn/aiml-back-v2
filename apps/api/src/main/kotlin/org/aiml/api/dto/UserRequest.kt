package org.aiml.api.dto

import org.aiml.user.application.RegisterCommand
import org.aiml.user.application.UpdateCommand

data class UserCreateRequest(
  val username: String,
  val password: String,
  val email: String,
  val firstName: String? = null,
  val lastName: String? = null,
  val bio: String? = null,
  val imageUrl: String? = null,
) {
  fun toCommand(): RegisterCommand = RegisterCommand(
    username = username,
    password = password,
    email = email,
    firstName = firstName,
    lastName = lastName,
    bio = bio,
    imageUrl = imageUrl
  )

}

data class UserUpdateRequest(
  val username: String? = null,
  val password: String? = null,
  val email: String? = null,
  val firstName: String? = null,
  val lastName: String? = null,
  val bio: String? = null,
  val imageUrl: String? = null,
) {
  fun toCommand(): UpdateCommand {
    return UpdateCommand(
      username = username,
      password = password,
      email = email,
      firstName = firstName,
      lastName = lastName,
      bio = bio,
      imageUrl = imageUrl
    )
  }

}
