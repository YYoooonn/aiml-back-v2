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
  override fun findProjectsByUserId(userId: UUID): List<ProjectUserDTO> {
    return projectUserQueryPort.findByUserId(userId).getOrThrow()
      .map { ProjectUserDTO.from(it) }
  }

  override fun findUserOwnedProjects(userId: UUID): List<ProjectUserDTO> {
    return findProjectsByUserId(userId).filter { it.role == ProjectUserRole.OWNER }
  }

  override fun findUsersByProjectId(userId: UUID, projectId: UUID): List<ProjectUserDTO> {
    return projectUserQueryPort.findByProjectId(projectId).getOrThrow()
      .map { ProjectUserDTO.from(it) }
  }

  override fun findProjectUser(userId: UUID, projectId: UUID): ProjectUserDTO {
    return projectUserQueryPort.findByProjectIdAndUserId(projectId, userId).getOrThrow()
      .let { ProjectUserDTO.from(it) }
  }

  override fun findAll(): List<ProjectUserDTO> {
    return projectUserQueryPort.findAll().getOrThrow()
      .map { ProjectUserDTO.from(it) }
  }

}
