package org.aiml.user.infra.persistence

import org.aiml.user.domain.model.User
import org.aiml.user.domain.model.UserProfile
import org.aiml.user.domain.port.outbound.UserProfilePersistencePort
import org.aiml.user.infra.persistence.entity.UserEntity
import org.aiml.user.infra.persistence.entity.UserProfileEntity
import org.aiml.user.infra.persistence.repository.UserProfileRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserProfilePersistenceAdapter(
  private val userProfileRepository: UserProfileRepository,
) : UserProfilePersistencePort {
  override fun save(profile: UserProfile, user: User): UserProfile {
    val entity = UserProfileEntity.from(profile, UserEntity.from(user))
    val saved = userProfileRepository.save(entity)
    return saved.toDomain()
  }

  override fun findByUserId(userId: UUID): UserProfile? {
    return userProfileRepository.findByUserId(userId)?.toDomain()
  }

  override fun delete(userId: UUID) {
    userProfileRepository.deleteById(userId)
  }

  override fun findByUser(user: User): UserProfile? {
    // FIXME 굳이?
    val userEntity = UserEntity.from(user)
    return userProfileRepository.findByUser(userEntity)?.toDomain()
  }

  override fun findByUsername(username: String): UserProfile? {
    return userProfileRepository.findByUsername(username)?.toDomain()
  }


  override fun update(userProfile: UserProfile): UserProfile? {
    TODO("Not yet implemented")
  }
}
