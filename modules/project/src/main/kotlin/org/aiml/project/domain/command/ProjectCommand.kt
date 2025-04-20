package org.aiml.project.domain.command

import java.util.*

data class CreateProjectCommand(
  val title: String,
  val subtitle: String? = null,
  val description: String? = null,
  val isPublic: Boolean = true,
)

data class UpdateProjectCommand(
  val id: UUID,
  val title: String? = null,
  val subtitle: String? = null,
  val description: String? = null,
  val isPublic: Boolean? = null,
)
