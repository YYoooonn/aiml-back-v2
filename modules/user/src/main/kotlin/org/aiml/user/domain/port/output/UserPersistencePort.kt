package org.aiml.user.domain.port.output

import org.aiml.user.domain.model.User
import java.util.UUID

interface UserPersistencePort {
  fun save(user: User): User
  fun update(user: User): User
  fun findAll(): List<User>
  fun findById(id: UUID): User?
  fun findByEmail(email: String): User?
  fun findByUsername(username: String): User?
  fun existsById(id: UUID): Boolean
  fun existsByUsername(username: String): Boolean
}
