package org.aiml.project.application.dto

import org.aiml.project.domain.model.Project
import org.aiml.project.domain.model.ProjectStatus
import java.time.LocalDateTime
import java.util.*

data class ProjectDTO(
  val id: UUID? = null,
  val title: String = "untitled project",
  val subtitle: String? = null,
  val description: String? = null,
  val isPublic: Boolean = true,
  val createdAt: LocalDateTime = LocalDateTime.now(),
  val updatedAt: LocalDateTime = LocalDateTime.now(),
) {
  companion object {
    fun from(project: Project): ProjectDTO = ProjectDTO(
      id = project.id,
      title = project.title,
      description = project.description,
      isPublic = project.status == ProjectStatus.PUBLIC,
      createdAt = project.createdAt,
      updatedAt = project.updatedAt,
    )
  }

  fun toDomain(): Project = Project(
    id = id ?: UUID.randomUUID(),
    title = title,
    subtitle = subtitle,
    description = description,
    status = if (isPublic) ProjectStatus.PUBLIC else ProjectStatus.PRIVATE
  )
}
