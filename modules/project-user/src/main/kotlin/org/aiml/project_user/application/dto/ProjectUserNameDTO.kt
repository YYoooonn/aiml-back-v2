package org.aiml.project_user.application.dto

import org.aiml.project_user.domain.model.ProjectUserRole
import java.util.*

data class ProjectUserNameDTO(
  val projectId: UUID,
  val username: String,
  val role: ProjectUserRole
) {
  companion object {
    fun from(dto: ProjectUserDTO, username: String) = ProjectUserNameDTO(
      projectId = dto.projectId,
      username = username,
      role = dto.role
    )
  }
}
