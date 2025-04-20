package org.aiml.user.domain.port.outbound

import org.aiml.user.domain.model.UserProfile
import org.aiml.user.domain.model.User
import java.util.UUID

interface UserProfilePersistencePort {
  fun save(profile: UserProfile, user: User): UserProfile?
  fun delete(userId: UUID)
  fun findByUserId(userId: UUID): UserProfile?
  fun update(userProfile: UserProfile): UserProfile?

  fun findByUsername(username: String): UserProfile?
  fun findByUser(user: User): UserProfile?
}
