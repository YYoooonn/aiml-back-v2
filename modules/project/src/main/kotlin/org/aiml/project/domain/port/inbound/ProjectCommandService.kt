package org.aiml.project.domain.port.inbound

import org.aiml.project.application.dto.ProjectDTO
import java.util.*

interface ProjectCommandService {
  fun create(dto: ProjectDTO): ProjectDTO
  fun update(dto: ProjectDTO): ProjectDTO
  fun delete(projectId: UUID)
}
