package org.aiml.api.dto.project

import org.aiml.project.application.dto.ProjectDTO

data class ProjectRequest(
  val title: String,
  val description: String? = null,
  val subtitle: String? = null,
  val isPublic: Boolean = true,
) {
  fun toDTO(): ProjectDTO {
    return ProjectDTO(
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
)
