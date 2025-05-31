package org.aiml.user.infra.persistence.repository

import org.aiml.user.infra.persistence.entity.UserEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface UserCoreRepository : JpaRepository<UserEntity, UUID> {
  fun findByUsername(username: String): UserEntity?
  fun findByEmail(email: String): UserEntity?
  fun existsByUsername(username: String): Boolean
  fun existsByEmail(email: String): Boolean

  @Query(
    """
  SELECT u FROM UserEntity u
  WHERE LOWER(u.username) = LOWER(:username)
  OR (LOWER(u.username) LIKE LOWER(CONCAT('%', :username, '%')) AND LOWER(u.username) <> LOWER(:username))
  ORDER BY CASE WHEN LOWER(u.username) = LOWER(:username) THEN 0 ELSE 1 END, u.username
  """
  )
  fun searchByUsernamePriority(username: String, pageable: Pageable): Page<UserEntity>
}
