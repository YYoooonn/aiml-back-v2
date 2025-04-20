package org.aiml.project_user.domain.facade.dto

import org.aiml.project.domain.model.Project
import org.aiml.project.domain.model.ProjectStatus
import org.aiml.project_user.domain.model.ProjectUser
import org.aiml.project_user.domain.model.ProjectUserRole
import java.time.LocalDateTime
import java.util.*

data class ProjectDTO(
  val id: UUID,
  val title: String,
  val subtitle: String?,
  val description: String? = null,
  val status: String,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime,
  // user role
  val role: String
) {
  companion object {
    fun from(project: Project, projectUser: ProjectUser): ProjectDTO {
      val statusString = when (project.status) {
        ProjectStatus.PUBLIC -> "public"
        ProjectStatus.PRIVATE -> "private"
        ProjectStatus.DRAFT -> "draft"
      }

      val roleString = when (projectUser.role) {
        ProjectUserRole.OWNER -> "owner"
        ProjectUserRole.VIEWER -> "viewer"
        ProjectUserRole.EDITOR -> "editor"
      }

      return ProjectDTO(
        id = project.id,
        title = project.title,
        subtitle = project.subtitle,
        description = project.description,
        status = statusString,
        createdAt = project.createdAt,
        updatedAt = project.updatedAt,
        role = roleString
      )
    }
  }
}
