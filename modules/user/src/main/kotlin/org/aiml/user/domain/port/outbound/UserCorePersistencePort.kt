package org.aiml.user.domain.port.outbound

import org.aiml.user.domain.model.User
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
}
