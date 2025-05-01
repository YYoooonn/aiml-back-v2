package org.aiml.user.domain.port.outbound

import org.aiml.user.domain.model.UserProfile
import java.util.UUID

interface UserProfilePersistencePort {
  fun save(profile: UserProfile): Result<UserProfile>
  fun deleteByUserId(userId: UUID): Result<Unit>

  // fun update(userProfile: UserProfile): Result<UserProfile>

  fun findAll(): Result<List<UserProfile>>
  fun findByUserId(userId: UUID): Result<UserProfile?>
//  fun findByUsername(username: String): Result<UserProfile?>
}
