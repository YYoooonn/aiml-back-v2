package org.aiml.api.mapper

import org.aiml.api.dto.MultiProjectResponse
import org.aiml.api.dto.ProjectBaseResponse
import org.aiml.api.dto.ProjectCreateRequest
import org.aiml.api.dto.ProjectUpdateRequest
import org.aiml.project.domain.command.CreateProjectCommand
import org.aiml.project.domain.command.UpdateProjectCommand
import org.aiml.project_user.domain.facade.dto.ProjectDTO
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProjectMapper {
  fun toProjectResponse(project: ProjectDTO): ProjectBaseResponse = ProjectBaseResponse(
    id = project.id,
    title = project.title,
    description = project.description,
    subtitle = project.subtitle,
    status = project.status,
    createdAt = project.createdAt,
    updatedAt = project.updatedAt,
  )

  fun toMultiProjectResponse(projects: List<ProjectDTO>): MultiProjectResponse = MultiProjectResponse(
    data = projects.map { toProjectResponse(it) }
  )

  fun toCreateCommand(req: ProjectCreateRequest) = CreateProjectCommand(
    title = req.title,
    description = req.description,
    subtitle = req.subtitle,
    isPublic = req.isPublic,
  )

  fun toUpdateCommand(id: UUID, req: ProjectUpdateRequest) = UpdateProjectCommand(
    id = id,
    title = req.title,
    description = req.description,
    subtitle = req.subtitle,
    isPublic = req.isPublic,
  )
}
