package org.aiml.user.domain.model

import java.time.LocalDateTime
import java.util.UUID

data class UserProfile(
  val id: UUID = UUID.randomUUID(),
  val userId: UUID,
  val firstName: String? = null,
  val lastName: String? = null,
  val bio: String? = null,
  var imageUrl: String? = null,
  val createdAt: LocalDateTime = LocalDateTime.now(),
  val updatedAt: LocalDateTime = LocalDateTime.now(),
)
//{
//  companion object {
//    fun from(command: CreateUserProfileCommand): UserProfile {
//      return UserProfile(
//        firstName = command.firstname,
//        lastName = command.lastname,
//        bio = command.bio,
//        imageUrl = command.imageUrl,
//      )
//    }
//  }
//
//  fun update(command: UpdateUserProfileCommand): UserProfile {
//    return this.copy(
//      firstName = command.firstname ?: firstName,
//      lastName = command.lastname ?: lastName,
//      bio = command.bio ?: bio,
//      imageUrl = command.imageUrl ?: imageUrl,
//    )
//  }
//}
