package org.aiml.project.domain.port.inbound

import org.aiml.project.application.dto.ProjectDTO
import java.util.*

interface ProjectQueryService {
  fun findById(id: UUID): ProjectDTO
  fun findByIds(ids: List<UUID>): List<ProjectDTO>

  fun findAll(): List<ProjectDTO>
}
