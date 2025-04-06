package org.aiml.user.core.domain.model

import java.time.LocalDateTime

data class Project(
  val id: Long?,
  val title: String,
  val subtitle: String? = null,
  val description: String? = null,
  val updatedAt: LocalDateTime,
  val createdAt: LocalDateTime
)
