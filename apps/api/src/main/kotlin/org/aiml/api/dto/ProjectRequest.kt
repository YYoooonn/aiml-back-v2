package org.aiml.api.dto

data class ProjectCreateRequest(
  val title: String,
  val description: String? = null,
  val subtitle: String? = null,
  val isPublic: Boolean = true,
)

data class ProjectUpdateRequest(
  val title: String? = null,
  val description: String? = null,
  val subtitle: String? = null,
  val isPublic: Boolean? = null,
)

data class ProjectParticipantRequest(
  val username: String,
  val role: String
)
