package org.aiml.project.domain.model

import java.time.LocalDateTime
import java.util.*

data class Project(
  val id: UUID,
  val title: String,
  val subtitle: String? = null,
  val description: String? = null,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime,
)
