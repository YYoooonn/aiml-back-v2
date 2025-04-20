package org.aiml.project.domain.model

import org.aiml.project.domain.command.CreateProjectCommand
import org.aiml.project.domain.command.UpdateProjectCommand
import java.time.LocalDateTime
import java.util.*

data class Project(
  val id: UUID = UUID.randomUUID(),
  val title: String,
  val subtitle: String? = null,
  val description: String? = null,
  val status: ProjectStatus = ProjectStatus.PUBLIC,
  val createdAt: LocalDateTime = LocalDateTime.now(),
  val updatedAt: LocalDateTime = LocalDateTime.now(),
) {
  companion object {
    fun from(command: CreateProjectCommand): Project = Project(
      title = command.title,
      subtitle = command.subtitle,
      description = command.description,
      status = if (command.isPublic) ProjectStatus.PUBLIC else ProjectStatus.PRIVATE
    )
  }

  fun update(command: UpdateProjectCommand): Project {
    val status = when (command.isPublic) {
      true -> ProjectStatus.PUBLIC
      false -> ProjectStatus.PRIVATE
      null -> status
    }
    return this.copy(
      title = command.title ?: title,
      subtitle = command.subtitle ?: subtitle,
      description = command.description ?: description,
      status = status
    )
  }

}

enum class ProjectStatus {
  PUBLIC, PRIVATE, DRAFT
}
