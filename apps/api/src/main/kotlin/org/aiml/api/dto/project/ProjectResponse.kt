package org.aiml.api.dto.project

import org.aiml.project.application.dto.ProjectDTO
import org.aiml.project_user.application.dto.ProjectUserNameDTO
import java.time.LocalDateTime
import java.util.*

data class ProjectBaseResponse(
  val id: UUID,
  val title: String,
  val description: String? = null,
  val subtitle: String? = null,
  val isPublic: Boolean? = true,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime,
) {
  companion object {
    fun from(dto: ProjectDTO): ProjectBaseResponse = ProjectBaseResponse(
      id = dto.id!!,
      title = dto.title,
      description = dto.description,
      subtitle = dto.subtitle,
      isPublic = dto.isPublic,
      createdAt = dto.createdAt,
      updatedAt = dto.updatedAt,
    )
  }
}

data class ProjectUserResponse(
  val projectId: UUID,
  val username: String,
  val role: String,
) {
  companion object {
    fun from(dto: ProjectUserNameDTO): ProjectUserResponse = ProjectUserResponse(
      projectId = dto.projectId,
      username = dto.username,
      role = dto.role.toString(),
    )
  }
}
