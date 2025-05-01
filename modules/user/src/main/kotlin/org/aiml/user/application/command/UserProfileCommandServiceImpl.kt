package org.aiml.user.application.command

import org.aiml.user.application.dto.UserProfileDTO
import org.aiml.user.domain.port.inbound.UserProfileCommandService
import org.aiml.user.domain.port.outbound.UserProfilePersistencePort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserProfileCommandServiceImpl(
  private val userProfilePersistencePort: UserProfilePersistencePort
) : UserProfileCommandService {

  // create save 차이 현재 없음
  override fun create(dto: UserProfileDTO): UserProfileDTO {
    val profile = userProfilePersistencePort.save(dto.toDomain()).getOrThrow()
    return UserProfileDTO.from(profile)
  }

  override fun update(dto: UserProfileDTO): UserProfileDTO {
    val profile = userProfilePersistencePort.save(dto.toDomain()).getOrThrow()
    return UserProfileDTO.from(profile)
  }

  override fun deleteByUserId(userId: UUID) {
    userProfilePersistencePort.deleteByUserId(userId).getOrThrow()
  }
}
