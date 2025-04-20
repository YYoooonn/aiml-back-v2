package org.aiml.api.mapper

import org.aiml.api.dto.MultiProjectUserResponse
import org.aiml.api.dto.ProjectParticipantRequest
import org.aiml.api.dto.ProjectUserResponse
import org.aiml.project_user.domain.command.ParticipantCommand
import org.aiml.project_user.domain.command.DeleteParticipantCommand
import org.aiml.project_user.domain.facade.dto.ProjectUserDTO
import org.aiml.project_user.domain.model.ProjectUserRole
import org.springframework.stereotype.Component
import java.util.*

@Component
class ParticipantMapper {
  fun toResponse(projectUser: ProjectUserDTO): ProjectUserResponse = ProjectUserResponse(
    projectId = projectUser.projectId,
    username = projectUser.username,
    role = projectUser.role,
  )

  fun toResponses(projectUsers: List<ProjectUserDTO>): MultiProjectUserResponse = MultiProjectUserResponse(
    data = projectUsers.map { toResponse(it) },
  )

  fun toCreateCommand(pId: UUID, req: ProjectParticipantRequest): ParticipantCommand {
    val role = when (req.role) {
      "editor" -> ProjectUserRole.EDITOR
      "owner" -> ProjectUserRole.OWNER
      else -> ProjectUserRole.VIEWER
    }

    return ParticipantCommand(
      projectId = pId,
      role = role,
      username = req.username,
    )
  }

  fun toDeleteCommand(username: String, projectId: UUID): DeleteParticipantCommand = DeleteParticipantCommand(
    username = username,
    projectId = projectId
  )
}
