package org.aiml.user.infra.persistence.entity

import jakarta.persistence.*
import org.aiml.libs.common.entity.BaseEntity
import org.aiml.user.domain.model.UserProfile
import java.util.UUID

@Entity
@Table(name = "user_profiles")
data class UserProfileEntity(
  @Id
  val id: UUID = UUID.randomUUID(),

  @Column(nullable = false, unique = true)
  val userId: UUID,

  val firstName: String? = null,

  val lastName: String? = null,

  val bio: String? = null,

  val imageUrl: String? = null,

  ) : BaseEntity() {
  companion object {
    fun from(domain: UserProfile): UserProfileEntity {
      return UserProfileEntity(
        id = domain.id,
        bio = domain.bio,
        imageUrl = domain.imageUrl,
        firstName = domain.firstName,
        lastName = domain.lastName,
        userId = domain.userId,
      )
    }
  }

  fun toDomain(): UserProfile {
    return UserProfile(
      userId = userId,
      bio = bio,
      imageUrl = imageUrl,
      firstName = firstName,
      lastName = lastName,
      createdAt = createdAt,
      updatedAt = updatedAt,
    )
  }
}
