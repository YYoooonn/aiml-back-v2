package org.aiml.user.application.query

import org.aiml.user.application.dto.UserCoreDTO
import org.aiml.user.domain.port.inbound.UserCoreQueryService
import org.aiml.user.domain.port.outbound.UserCorePersistencePort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserCoreQueryServiceImpl(
  private val userCorePersistencePort: UserCorePersistencePort
) : UserCoreQueryService {
  override fun findById(id: UUID): UserCoreDTO {
    val user = userCorePersistencePort.findById(id).getOrThrow()
    return UserCoreDTO.from(user)
  }

  override fun findByIds(ids: List<UUID>): List<UserCoreDTO> {
    return userCorePersistencePort.findByIds(ids).getOrThrow()
      .map { UserCoreDTO.from(it) }
  }

  override fun findByEmail(email: String): UserCoreDTO? {
    return userCorePersistencePort.findByEmail(email).getOrNull()?.let { UserCoreDTO.from(it) }
  }

  override fun findByUsername(username: String): UserCoreDTO? {
    return userCorePersistencePort.findByUsername(username).getOrNull()?.let { UserCoreDTO.from(it) }
  }

  override fun searchByUsername(username: String, pageable: Pageable): Page<UserCoreDTO> {
    return userCorePersistencePort.searchUsersByUsername(username, pageable).getOrThrow().map { UserCoreDTO.from(it) }
  }


  override fun findAll(): List<UserCoreDTO> {
    return userCorePersistencePort.findAll().getOrThrow().map { UserCoreDTO.from(it) }
  }
}
