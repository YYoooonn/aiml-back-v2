package org.aiml.project_user.domain.facade.dto

import org.aiml.project_user.domain.model.ProjectUser
import org.aiml.project_user.domain.model.ProjectUserRole
import org.aiml.user.domain.model.User
import java.util.*

data class ProjectUserDTO(
  val projectId: UUID,
  val username: String,
  val role: String,
) {
  companion object {
    fun from(user: User, projectUser: ProjectUser): ProjectUserDTO {
      val roleString = when (projectUser.role) {
        ProjectUserRole.OWNER -> "owner"
        ProjectUserRole.EDITOR -> "editor"
        ProjectUserRole.VIEWER -> "viewer"
      }
      return ProjectUserDTO(
        projectId = projectUser.projectId,
        username = user.username,
        role = roleString,
      )

    }
  }
}
