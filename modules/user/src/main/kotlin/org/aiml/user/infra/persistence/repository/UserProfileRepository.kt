package org.aiml.user.infra.persistence.repository

import org.aiml.user.infra.entity.UserEntity
import org.aiml.user.infra.entity.UserProfileEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserProfileRepository : JpaRepository<UserProfileEntity, UUID> {
  fun findByUser(user: UserEntity): UserProfileEntity?
}
