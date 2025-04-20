package org.aiml.api.dto

import java.time.LocalDateTime
import java.util.*

data class ProjectBaseResponse(
  val id: UUID,
  val title: String,
  val description: String? = null,
  val subtitle: String? = null,
  val status: String,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime,
)

data class MultiProjectResponse(
  val data: List<ProjectBaseResponse>
)

data class ProjectUserResponse(
  val projectId: UUID,
  val username: String,
  val role: String,
)

data class MultiProjectUserResponse(
  val data: List<ProjectUserResponse>
)
