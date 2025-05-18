package org.aiml.project_user.domain.port.inbound

import org.aiml.project_user.application.dto.ProjectUserDTO
import java.util.*

interface ProjectUserCommandService {
  fun create(dto: ProjectUserDTO): ProjectUserDTO
  fun update(dto: ProjectUserDTO): ProjectUserDTO
  fun delete(projectId: UUID, userId: UUID)
  fun deleteAllByProjectId(projectId: UUID)
  fun deleteAllByUserId(userId: UUID)

  fun deleteAll()
}
