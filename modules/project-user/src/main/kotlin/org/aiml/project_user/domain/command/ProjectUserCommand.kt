package org.aiml.project_user.domain.command

import org.aiml.project_user.domain.model.ProjectUserRole
import java.util.*


// TODO move mapping to dto
data class ProjectUserCommand(
  val projectId: UUID,
  val userId: UUID,
  val role: ProjectUserRole,
) {
  companion object {
    fun from(uId: UUID, command: ParticipantCommand): ProjectUserCommand = ProjectUserCommand(
      userId = uId,
      projectId = command.projectId,
      role = command.role
    )

    fun fromCreation(uId: UUID, pId: UUID): ProjectUserCommand = ProjectUserCommand(
      userId = uId,
      projectId = pId,
      role = ProjectUserRole.OWNER
    )
  }
}

data class ParticipantCommand(
  val projectId: UUID,
  val role: ProjectUserRole,
  val username: String,
)

data class DeleteParticipantCommand(
  val projectId: UUID,
  val username: String,
)

data class ParticipantUpdateCommand(
  val username: String,
  val projectId: UUID,
  val role: ProjectUserRole? = null,
)
