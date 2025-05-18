package org.aiml.user.infra.persistence.repository

import org.aiml.user.infra.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserCoreRepository : JpaRepository<UserEntity, UUID> {
  fun findByUsername(username: String): UserEntity?
  fun findByEmail(email: String): UserEntity?
  fun existsByUsername(username: String): Boolean
  fun existsByEmail(email: String): Boolean
}
