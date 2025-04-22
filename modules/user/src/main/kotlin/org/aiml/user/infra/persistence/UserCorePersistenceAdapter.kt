package org.aiml.user.infra.persistence

import org.aiml.user.domain.model.User
import org.aiml.user.domain.port.outbound.UserCorePersistencePort
import org.aiml.user.infra.persistence.entity.UserEntity
import org.aiml.user.infra.persistence.repository.UserCoreRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserCorePersistenceAdapter(
  private val userCoreRepository: UserCoreRepository,
) : UserCorePersistencePort {
  override fun save(user: User): User {
    val entity = UserEntity.from(user)
    val saved = userCoreRepository.save(entity)
    return saved.toDomain()
  }

  override fun delete(id: UUID) {
    userCoreRepository.deleteById(id)
  }

  override fun findById(id: UUID): User? {
    val userEntity = userCoreRepository.findById(id).orElse(null)
    return userEntity?.toDomain()
  }

  override fun existsById(id: UUID): Boolean {
    return userCoreRepository.existsById(id)
  }

  override fun existsByUsername(username: String): Boolean {
    return userCoreRepository.existsByUsername(username)
  }

  override fun existsByEmail(email: String): Boolean {
    return userCoreRepository.existsByEmail(email)
  }

  override fun findByEmail(email: String): User? {
    return userCoreRepository.findByEmail(email)?.toDomain()
  }

  override fun findByUsername(username: String): User? {
    return userCoreRepository.findByUsername(username)?.toDomain()
  }

  override fun findAll(): List<User> {
    return userCoreRepository.findAll().map { it.toDomain() }
  }

}
