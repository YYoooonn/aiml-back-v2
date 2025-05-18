package org.aiml.user.domain.port.inbound

import org.aiml.user.application.dto.UserProfileDTO
import java.util.*

interface UserProfileCommandService {
  fun create(dto: UserProfileDTO): UserProfileDTO
  fun update(dto: UserProfileDTO): UserProfileDTO
  fun deleteByUserId(userId: UUID)

  fun deleteAll()
}
