package org.aiml.user.application.query

import org.aiml.user.application.dto.UserProfileDTO
import org.aiml.user.domain.port.inbound.UserProfileQueryService
import org.aiml.user.domain.port.outbound.UserProfilePersistencePort
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserProfileQueryServiceImpl(
  private val userProfilePersistencePort: UserProfilePersistencePort
) : UserProfileQueryService {

  override fun findByUserId(userId: UUID): UserProfileDTO? {
    return userProfilePersistencePort.findByUserId(userId).getOrNull()
      ?.let { UserProfileDTO.from(it) }
  }

  override fun findAll(): List<UserProfileDTO> {
    return userProfilePersistencePort.findAll().getOrThrow()
      .map { UserProfileDTO.from(it) }
  }

  override fun findById(id: UUID): UserProfileDTO {
    TODO("Not yet implemented")
  }

  fun findByUsername(username: String): UserProfileDTO? {
    TODO("Not yet implemented")
  }
}
