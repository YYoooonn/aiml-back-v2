package org.aiml.user.core.application.port.outgoing

import org.aiml.user.core.domain.model.User

interface UserPersistencePort {
  fun save(user: User): User
  fun findById(id: Long): User?
  fun findByEmail(email: String): User?
  fun findByUsername(username: String): User?
}
