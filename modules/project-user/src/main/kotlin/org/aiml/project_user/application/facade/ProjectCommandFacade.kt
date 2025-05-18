package org.aiml.project_user.application.facade

import jakarta.transaction.Transactional
import org.aiml.project.application.dto.ProjectDTO
import org.aiml.project.domain.port.inbound.ProjectCommandService
import org.aiml.project_user.application.ProjectUserAuthService
import org.aiml.project_user.application.dto.ProjectUserDTO
import org.aiml.project_user.domain.port.inbound.ProjectUserCommandService
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProjectCommandFacade(
  private val projectCommandService: ProjectCommandService,
  private val projectUserCommandService: ProjectUserCommandService,
  private val authService: ProjectUserAuthService
) {
  @Transactional
  fun createProject(userId: UUID, project: ProjectDTO): ProjectDTO {
    val newProject = projectCommandService.create(project)
    projectUserCommandService.create(ProjectUserDTO.asOwner(userId, newProject.id!!))
    return newProject
  }

  fun updateProject(userId: UUID, project: ProjectDTO): ProjectDTO {
    if (project.id == null) throw RuntimeException("id must be provided for update")
    authService.authenticateEditor(userId, project.id!!)
    return projectCommandService.update(project)
  }

  @Transactional
  fun deleteProject(userId: UUID, projectId: UUID) {
    authService.authenticateOwner(userId, projectId)
    projectCommandService.deleteById(projectId)
    projectUserCommandService.deleteAllByProjectId(projectId)
  }
}
