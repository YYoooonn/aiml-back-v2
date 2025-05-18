package org.aiml.user.infra.persistence.repository

import org.aiml.user.infra.persistence.entity.UserProfileEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserProfileRepository : JpaRepository<UserProfileEntity, UUID> {
  fun findByUserId(userId: UUID): UserProfileEntity?
  fun deleteByUserId(userId: UUID)

//  @Query("SELECT p FROM UserProfileEntity p WHERE p.user.username = :username")
//  fun findByUsername(@Param("username") username: String): UserProfileEntity?
}
