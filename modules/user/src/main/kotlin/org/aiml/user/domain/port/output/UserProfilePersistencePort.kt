package org.aiml.user.domain.port.output

import org.aiml.user.domain.model.UserProfile
import org.aiml.user.domain.model.User
import java.util.UUID

interface UserProfilePersistencePort {
  fun findByUser(user: User): UserProfile?
  fun save(profile: UserProfile, user: User): UserProfile
  fun update(userProfile: UserProfile): UserProfile?
}
