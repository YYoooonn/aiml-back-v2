package org.aiml.user.domain.port.inbound

import org.aiml.user.application.dto.UserProfileDTO
import java.util.UUID

interface UserProfileQueryService {
  fun findById(id: UUID): UserProfileDTO
  fun findByUserId(userId: UUID): UserProfileDTO?

  fun findAll(): List<UserProfileDTO>
  // fun findByUsername(username: String): UserProfileDTO?
}
