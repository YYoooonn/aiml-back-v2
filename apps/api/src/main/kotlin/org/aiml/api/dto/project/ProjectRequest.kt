package org.aiml.api.dto.project

import org.aiml.project.application.dto.ProjectDTO
import org.aiml.project_user.application.dto.ProjectUserNameDTO
import org.aiml.project_user.domain.model.ProjectUserRole
import java.util.*

data class ProjectRequest(
  val title: String,
  val description: String? = null,
  val subtitle: String? = null,
  val isPublic: Boolean = true,
) {
  fun toDTO(id: UUID? = null): ProjectDTO {
    return ProjectDTO(
      id = id ?: UUID.randomUUID(),
      title = title,
      subtitle = subtitle,
      description = description,
      isPublic = isPublic
    )
  }
}

data class ProjectParticipantRequest(
  val username: String,
  val role: String
) {
  fun toDTO(projectId: UUID): ProjectUserNameDTO {
    val pRole = when (role) {
      "EDITOR" -> ProjectUserRole.EDITOR
      "OWNER" -> ProjectUserRole.OWNER
      "VIEWER" -> ProjectUserRole.VIEWER
      else -> throw IllegalStateException("Unknown role $role, must be EDITOR, OWNER, VIEWER")
    }
    return ProjectUserNameDTO(
      projectId = projectId,
      username = username,
      role = pRole
    )
  }
}
