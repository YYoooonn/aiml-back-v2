package org.aiml.project_user.application

import org.aiml.project_user.application.dto.ProjectUserDTO
import org.aiml.project_user.domain.model.ProjectUserRole
import org.aiml.project_user.domain.port.inbound.ProjectUserQueryService
import org.aiml.project_user.domain.port.outbound.ProjectUserQueryPort
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProjectUserQueryServiceImpl(
  private val projectUserQueryPort: ProjectUserQueryPort,
) : ProjectUserQueryService {
  override fun findProjectsByUserId(userId: UUID): Result<List<ProjectUserDTO>> = runCatching {
    projectUserQueryPort.findByUserId(userId).getOrThrow()
      .map { ProjectUserDTO.from(it) }
  }

  override fun findUsersByProjectId(userId: UUID, projectId: UUID): Result<List<ProjectUserDTO>> = runCatching {
    projectUserQueryPort.findByProjectId(projectId).getOrThrow()
      .map { ProjectUserDTO.from(it) }
  }

  override fun findProjectUser(userId: UUID, projectId: UUID): Result<ProjectUserDTO> = runCatching {
    projectUserQueryPort.findByProjectIdAndUserId(projectId, userId).getOrThrow()
      .let { ProjectUserDTO.from(it) }
  }
}
