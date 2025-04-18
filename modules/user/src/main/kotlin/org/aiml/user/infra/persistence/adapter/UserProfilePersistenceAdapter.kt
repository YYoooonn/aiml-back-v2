package org.aiml.user.infra.persistence.adapter

import org.aiml.user.domain.model.User
import org.aiml.user.domain.model.UserProfile
import org.aiml.user.domain.port.output.UserProfilePersistencePort
import org.aiml.user.infra.entity.UserEntity
import org.aiml.user.infra.entity.UserProfileEntity
import org.aiml.user.infra.persistence.repository.UserProfileRepository
import org.aiml.user.infra.persistence.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserProfilePersistenceAdapter(
  private val userProfileRepository: UserProfileRepository,
  private val userRepository: UserRepository,
) : UserProfilePersistencePort {
  override fun save(profile: UserProfile, user: User): UserProfile {
    val entity = UserProfileEntity.from(profile, UserEntity.from(user))
    val saved = userProfileRepository.save(entity)
    return saved.toDomain()
  }

  override fun findByUser(user: User): UserProfile? {
    val userEntity = UserEntity.from(user)
    return userProfileRepository.findByUser(userEntity)?.toDomain()
  }

  override fun update(userProfile: UserProfile): UserProfile? {
    TODO("Not yet implemented")
  }
}
