package org.aiml.user.application

import org.aiml.user.domain.command.CreateUserCoreCommand
import org.aiml.user.domain.command.CreateUserProfileCommand
import org.aiml.user.domain.command.UpdateUserCoreCommand
import org.aiml.user.domain.command.UpdateUserProfileCommand

data class RegisterCommand(
  val username: String,
  val password: String,
  val email: String,
  val firstName: String? = null,
  val lastName: String? = null,
  val bio: String? = null,
  val imageUrl: String? = null,
) {
  fun toCoreCommand() = CreateUserCoreCommand(
    email = email,
    username = username,
    password = password,
  )

  fun toProfileCommand() = CreateUserProfileCommand(
    firstname = firstName,
    lastname = lastName,
    bio = bio,
    imageUrl = imageUrl
  )

}

data class UpdateCommand(
  val username: String? = null,
  val password: String? = null,
  val email: String? = null,
  val firstName: String? = null,
  val lastName: String? = null,
  val bio: String? = null,
  val imageUrl: String? = null,
) {
  fun toCoreCommand() = UpdateUserCoreCommand(
    username = username,
    password = password,
    email = email,
  )

  fun toProfileCommand() = UpdateUserProfileCommand(
    firstname = firstName,
    lastname = lastName,
    bio = bio,
    imageUrl = imageUrl
  )
}
