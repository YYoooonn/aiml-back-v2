package org.aiml.project_user.application.dto

import org.aiml.project_user.domain.model.ProjectUser
import org.aiml.project_user.domain.model.ProjectUserRole
import java.util.*

data class ProjectUserDTO(
  val projectId: UUID,
  val userId: UUID,
  val role: ProjectUserRole,
) {
  companion object {
    fun from(projectUser: ProjectUser): ProjectUserDTO {
      return ProjectUserDTO(
        projectId = projectUser.projectId,
        userId = projectUser.userId,
        role = projectUser.role,
      )
    }

    fun build(projectId: UUID, userId: UUID, role: ProjectUserRole): ProjectUserDTO {
      return ProjectUserDTO(
        projectId = projectId,
        userId = userId,
        role = role
      )
    }

    fun asOwner(userId: UUID, projectId: UUID) = build(projectId, userId, role = ProjectUserRole.OWNER)
  }

  fun toDomain(): ProjectUser {
    return ProjectUser(
      projectId = projectId,
      userId = userId,
      role = role
    )
  }
}

