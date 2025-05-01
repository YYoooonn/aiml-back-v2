package org.aiml.user.domain.port.inbound

import org.aiml.user.application.dto.UserCoreDTO
import java.util.*

interface UserCoreQueryService {
  fun findById(id: UUID): UserCoreDTO
  fun findByIds(ids: List<UUID>): List<UserCoreDTO>
  fun findByUsername(username: String): UserCoreDTO?
  fun findByEmail(email: String): UserCoreDTO?

  fun findAll(): List<UserCoreDTO>
}
