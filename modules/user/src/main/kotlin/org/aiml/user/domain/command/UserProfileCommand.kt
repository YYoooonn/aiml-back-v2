package org.aiml.user.domain.command

import org.aiml.user.domain.model.UserProfile

data class CreateUserProfileCommand(
  val firstname: String? = null,
  val lastname: String? = null,
  val bio: String? = null,
  val imageUrl: String? = null,
)

data class UpdateUserProfileCommand(
  val firstname: String? = null,
  val lastname: String? = null,
  val bio: String? = null,
  val imageUrl: String? = null,
)
