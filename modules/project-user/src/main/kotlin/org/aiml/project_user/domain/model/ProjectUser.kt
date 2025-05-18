package org.aiml.project_user.domain.model

import java.util.*

data class ProjectUser(
  val projectId: UUID,
  val userId: UUID,
  val role: ProjectUserRole
)

enum class ProjectUserRole(private val level: Int) {
  OWNER(3), EDITOR(2), VIEWER(1);

  fun hasPermission(required: ProjectUserRole): Boolean = level >= required.level
}
