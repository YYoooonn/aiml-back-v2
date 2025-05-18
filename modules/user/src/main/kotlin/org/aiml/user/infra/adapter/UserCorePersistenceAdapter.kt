package org.aiml.user.infra.adapter

import org.aiml.user.domain.model.User
import org.aiml.user.domain.port.outbound.UserCorePersistencePort
import org.aiml.user.infra.persistence.entity.UserEntity
import org.aiml.user.infra.persistence.repository.UserCoreRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserCorePersistenceAdapter(
  private val userCoreRepository: UserCoreRepository,
) : UserCorePersistencePort {
  override fun save(user: User): Result<User> = runCatching {
    val entity = UserEntity.from(user)
    userCoreRepository.save(entity).toDomain()
  }

  override fun delete(id: UUID): Result<Unit> = runCatching {
    userCoreRepository.deleteById(id)
  }

  // query

  override fun findById(id: UUID): Result<User> = runCatching {
    val userEntity = userCoreRepository.findById(id).get()
    userEntity.toDomain()
  }

  override fun findByIds(ids: List<UUID>): Result<List<User>> = runCatching {
    userCoreRepository.findAllById(ids).map { it.toDomain() }
  }

  override fun findByEmail(email: String): Result<User?> = runCatching {
    userCoreRepository.findByEmail(email)?.toDomain()
  }

  override fun findByUsername(username: String): Result<User?> = runCatching {
    userCoreRepository.findByUsername(username)?.toDomain()
  }

  override fun findAll(): Result<List<User>> = runCatching {
    userCoreRepository.findAll().map { it.toDomain() }
  }
  // helper

  override fun existsById(id: UUID): Boolean {
    return userCoreRepository.existsById(id)
  }

  override fun existsByUsername(username: String): Boolean {
    return userCoreRepository.existsByUsername(username)
  }

  override fun existsByEmail(email: String): Boolean {
    return userCoreRepository.existsByEmail(email)
  }

  override fun deleteAll(): Result<Unit> = runCatching {
    userCoreRepository.deleteAll()
  }

}
