package org.aiml.user.core.domain.model

import java.time.LocalDateTime

data class User(
  val id: Long?,
  val username: String,
  val password: String,
  val email: String,
  val firstName: String? = null,
  val lastName: String? = null,
  val imgAddress: String? = null,
  val projects: List<UserProject> = emptyList(),

  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime
)


data class UserProject(
  val project: Project,
  val role: UserProjectRole,
  val permission: UserProjectPermission
)
