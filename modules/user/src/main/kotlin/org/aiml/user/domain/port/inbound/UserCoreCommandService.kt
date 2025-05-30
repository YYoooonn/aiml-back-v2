package org.aiml.user.domain.port.inbound

import org.aiml.user.application.dto.UserCoreDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

interface UserCoreCommandService {
  fun create(user: UserCoreDTO): UserCoreDTO
  fun update(user: UserCoreDTO): UserCoreDTO
  fun delete(id: UUID)


  fun deleteAll()
}
