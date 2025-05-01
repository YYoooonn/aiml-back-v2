package org.aiml.project_user.application.dto

import java.util.*

data class ProjectUserNameDTO(
  val projectId: UUID,
  val username: String,
  val role: String
) {
  companion object {
    fun from(dto: ProjectUserDTO, username: String) = ProjectUserNameDTO(
      projectId = dto.projectId,
      username = username,
      role = dto.role
    )
  }
}
