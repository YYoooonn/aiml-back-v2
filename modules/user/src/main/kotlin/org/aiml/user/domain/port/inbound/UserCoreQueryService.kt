package org.aiml.user.domain.port.inbound

import org.aiml.user.application.dto.UserCoreDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface UserCoreQueryService {
  fun findById(id: UUID): UserCoreDTO
  fun findByIds(ids: List<UUID>): List<UserCoreDTO>
  fun findByUsername(username: String): UserCoreDTO?
  fun findByEmail(email: String): UserCoreDTO?

  fun searchByUsername(username: String, pageable: Pageable): Page<UserCoreDTO>

  fun findAll(): List<UserCoreDTO>
}
