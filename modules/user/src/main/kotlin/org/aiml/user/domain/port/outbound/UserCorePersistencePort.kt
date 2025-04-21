package org.aiml.user.domain.port.outbound

import org.aiml.user.domain.model.User
import java.util.UUID

interface UserCorePersistencePort {
  fun save(user: User): User?
  fun findAll(): List<User>
  fun delete(id: UUID)
  fun findById(id: UUID): User?
  fun findByEmail(email: String): User?
  fun findByUsername(username: String): User?
  fun existsById(id: UUID): Boolean
  fun existsByEmail(email: String): Boolean
  fun existsByUsername(username: String): Boolean
}
