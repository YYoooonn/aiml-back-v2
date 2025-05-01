package org.aiml.project_user.application.facade

import org.aiml.project.application.dto.ProjectDTO
import org.aiml.project.domain.port.inbound.ProjectQueryService
import org.aiml.project_user.application.ProjectUserAuthService
import org.aiml.project_user.domain.port.inbound.ProjectUserQueryService
import org.springframework.stereotype.Service
import java.util.*

// project-user 를 통한 인증

@Service
class ProjectQueryFacade(
  private val projectQueryService: ProjectQueryService,
  private val projectUserQueryService: ProjectUserQueryService,
  private val authService: ProjectUserAuthService
) {
  fun loadProjects(userId: UUID): List<ProjectDTO> {
    val pIds = projectUserQueryService.findProjectsByUserId(userId)
      .getOrThrow()
      .map { it.projectId }
    return projectQueryService.findByIds(pIds)
  }

  fun loadProjectById(userId: UUID, projectId: UUID): ProjectDTO {
    authService.authenticateViewer(userId, projectId)
    return projectQueryService.findById(projectId)
  }
}
