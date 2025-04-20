package org.aiml.user.infra.persistence.repository

import org.aiml.user.infra.persistence.entity.UserEntity
import org.aiml.user.infra.persistence.entity.UserProfileEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface UserProfileRepository : JpaRepository<UserProfileEntity, UUID> {
  fun findByUser(user: UserEntity): UserProfileEntity?
  fun findByUserId(userId: UUID): UserProfileEntity?

  @Query("SELECT p FROM UserProfileEntity p WHERE p.user.username = :username")
  fun findByUsername(@Param("username") username: String): UserProfileEntity?
}
