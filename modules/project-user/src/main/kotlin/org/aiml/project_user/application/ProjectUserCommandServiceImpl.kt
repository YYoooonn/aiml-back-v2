package org.aiml.project_user.application

import org.aiml.project_user.application.dto.ProjectUserDTO
import org.aiml.project_user.domain.command.ProjectUserCommand
import org.aiml.project_user.domain.model.ProjectUser
import org.aiml.project_user.domain.model.ProjectUserRole
import org.aiml.project_user.domain.port.outbound.ProjectUserCommandPort
import org.aiml.project_user.domain.port.inbound.ProjectUserCommandService
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProjectUserCommandServiceImpl(
  private val projectUserCommandPort: ProjectUserCommandPort,
) : ProjectUserCommandService {
  override fun create(dto: ProjectUserDTO): ProjectUserDTO {
    return projectUserCommandPort.save(dto.toDomain()).getOrThrow()
      .let { ProjectUserDTO.from(it) }
  }

  override fun update(dto: ProjectUserDTO): ProjectUserDTO {
    return projectUserCommandPort.upsert(dto.toDomain()).getOrThrow()
      .let { ProjectUserDTO.from(it) }
  }

  override fun delete(projectId: UUID, userId: UUID) {
    return projectUserCommandPort.deleteByProjectIdAndUserId(projectId, userId).getOrThrow()
  }

  override fun deleteAllByProjectId(projectId: UUID) {
    return projectUserCommandPort.deleteAllProjectUserByProjectId(projectId).getOrThrow()
  }
}
