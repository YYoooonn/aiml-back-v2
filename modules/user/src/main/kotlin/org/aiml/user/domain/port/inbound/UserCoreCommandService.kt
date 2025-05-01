package org.aiml.user.domain.port.inbound

import org.aiml.user.application.dto.UserCoreDTO
import java.util.UUID

interface UserCoreCommandService {
  fun create(user: UserCoreDTO): UserCoreDTO
  fun update(user: UserCoreDTO): UserCoreDTO
  fun delete(id: UUID)
}
