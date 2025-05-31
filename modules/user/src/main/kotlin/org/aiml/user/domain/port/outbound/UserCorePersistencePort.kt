package org.aiml.user.domain.port.outbound

import org.aiml.user.domain.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

interface UserCorePersistencePort {
  fun save(user: User): Result<User>
  fun delete(id: UUID): Result<Unit>

  fun findAll(): Result<List<User>>
  fun findById(id: UUID): Result<User>
  fun findByIds(ids: List<UUID>): Result<List<User>>

  fun findByEmail(email: String): Result<User?>
  fun findByUsername(username: String): Result<User?>

  fun existsById(id: UUID): Boolean
  fun existsByEmail(email: String): Boolean
  fun existsByUsername(username: String): Boolean

  fun searchUsersByUsername(username: String, pageable: Pageable): Result<Page<User>>


  fun deleteAll(): Result<Unit>
}
