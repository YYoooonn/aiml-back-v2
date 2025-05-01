package org.aiml.project_user.domain.model

import org.aiml.project_user.domain.command.ProjectUserCommand
import java.util.*

data class ProjectUser(
  val projectId: UUID,
  val userId: UUID,
  val role: ProjectUserRole
) {
  companion object {
    fun asOwner(command: ProjectUserCommand): ProjectUser = ProjectUser(
      userId = command.userId,
      projectId = command.projectId,
      role = ProjectUserRole.OWNER
    )

    fun asViewer(command: ProjectUserCommand): ProjectUser = ProjectUser(
      userId = command.userId,
      projectId = command.projectId,
      role = ProjectUserRole.VIEWER
    )

    fun asEditor(command: ProjectUserCommand): ProjectUser = ProjectUser(
      userId = command.userId,
      projectId = command.projectId,
      role = ProjectUserRole.EDITOR
    )
  }
}

enum class ProjectUserRole(val level: Int) {
  OWNER(3), EDITOR(2), VIEWER(1);

  fun hasPermission(required: ProjectUserRole): Boolean = level >= required.level
}
