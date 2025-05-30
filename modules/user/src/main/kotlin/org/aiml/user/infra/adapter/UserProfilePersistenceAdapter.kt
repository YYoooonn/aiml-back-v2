package org.aiml.user.infra.adapter

import org.aiml.user.domain.model.UserProfile
import org.aiml.user.domain.port.outbound.UserProfilePersistencePort
import org.aiml.user.infra.persistence.entity.UserProfileEntity
import org.aiml.user.infra.persistence.repository.UserProfileRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserProfilePersistenceAdapter(
  private val userProfileRepository: UserProfileRepository,
) : UserProfilePersistencePort {

  override fun save(profile: UserProfile): Result<UserProfile> = runCatching {
    val entity = UserProfileEntity.from(profile)
    userProfileRepository.save(entity).toDomain()
  }

  override fun deleteByUserId(userId: UUID): Result<Unit> = runCatching {
    userProfileRepository.deleteByUserId(userId)
  }

  override fun upsert(profile: UserProfile): Result<UserProfile> = runCatching {
    val existing = userProfileRepository.findByUserId(profile.userId)
    if(existing != null) {
      val update = existing.copy(
        firstName = profile.firstName,
        lastName = profile.lastName,
        bio = profile.bio,
        imageUrl = profile.imageUrl,
        )
      userProfileRepository.save(update).toDomain()
    } else {
      val entity = UserProfileEntity.from(profile)
      userProfileRepository.save(entity).toDomain()
    }
  }


  // query
  override fun findAll(): Result<List<UserProfile>> = runCatching {
    userProfileRepository.findAll().map { it.toDomain() }
  }

  override fun findByUserId(userId: UUID): Result<UserProfile?> = runCatching {
    userProfileRepository.findByUserId(userId)?.toDomain()
  }

  override fun deleteAll(): Result<Unit> = runCatching {
    userProfileRepository.deleteAll()
  }

  // helper
  fun update(userProfile: UserProfile): Result<UserProfile> = runCatching {
    save(userProfile).getOrThrow()
  }

  fun create(userProfile: UserProfile): Result<UserProfile> = runCatching {
    TODO()
  }


}
