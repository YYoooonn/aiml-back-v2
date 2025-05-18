package org.aiml.project.domain.model

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
)

enum class ProjectStatus {
  PUBLIC, PRIVATE, DRAFT
}
