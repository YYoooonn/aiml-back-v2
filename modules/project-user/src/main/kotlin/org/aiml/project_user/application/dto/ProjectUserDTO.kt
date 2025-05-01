package org.aiml.project_user.application.dto

import org.aiml.project_user.domain.model.ProjectUser
import org.aiml.project_user.domain.model.ProjectUserRole
import java.util.*

data class ProjectUserDTO(
  val projectId: UUID,
  val userId: UUID,
  val role: String,
) {
  companion object {
    fun from(projectUser: ProjectUser): ProjectUserDTO {
      val roleString = when (projectUser.role) {
        ProjectUserRole.OWNER -> "owner"
        ProjectUserRole.EDITOR -> "editor"
        ProjectUserRole.VIEWER -> "viewer"
      }
      return ProjectUserDTO(
        projectId = projectUser.projectId,
        userId = projectUser.userId,
        role = roleString,
      )
    }

    fun build(projectId: UUID, userId: UUID, role: String): ProjectUserDTO {
      return ProjectUserDTO(
        projectId = projectId,
        userId = userId,
        role = role
      )
    }

    fun asOwner(userId: UUID, projectId: UUID) = build(projectId, userId, role = "owner")
  }

  fun toDomain(): ProjectUser {
    val role = when (role) {
      "owner" -> ProjectUserRole.OWNER
      "editor" -> ProjectUserRole.EDITOR
      "viewer" -> ProjectUserRole.VIEWER
      else -> throw IllegalStateException("must provide role : owner, editor, viewer")
    }
    return ProjectUser(
      projectId = projectId,
      userId = userId,
      role = role
    )
  }
}
