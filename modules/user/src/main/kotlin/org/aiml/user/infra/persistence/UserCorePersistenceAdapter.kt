package org.aiml.user.infra.persistence

import org.aiml.user.domain.model.User
import org.aiml.user.domain.port.outbound.UserCorePersistencePort
import org.aiml.user.infra.persistence.entity.UserEntity
import org.aiml.user.infra.persistence.repository.UserRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserCorePersistenceAdapter(
  private val userRepository: UserRepository,
) : UserCorePersistencePort {
  override fun save(user: User): User {
    val entity = UserEntity.from(user)
    val saved = userRepository.save(entity)
    return saved.toDomain()
  }

  override fun delete(id: UUID) {
    userRepository.deleteById(id)
  }

  override fun findById(id: UUID): User? {
    val userEntity = userRepository.findById(id).orElse(null)
    return userEntity?.toDomain()
  }

  override fun existsById(id: UUID): Boolean {
    return userRepository.existsById(id)
  }

  override fun existsByUsername(username: String): Boolean {
    return userRepository.existsByUsername(username)
  }

  override fun findByEmail(email: String): User? {
    return userRepository.findByEmail(email)?.toDomain()
  }

  override fun findByUsername(username: String): User? {
    return userRepository.findByUsername(username)?.toDomain()
  }

  override fun findAll(): List<User> {
    return userRepository.findAll().map { it.toDomain() }
  }

}
