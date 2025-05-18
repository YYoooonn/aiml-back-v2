package org.aiml.project_user.domain.port.outbound

import org.aiml.project_user.domain.model.ProjectUser
import java.util.*

interface ProjectUserCommandPort {
  fun save(projectUser: ProjectUser): Result<ProjectUser>
  fun upsert(projectUser: ProjectUser): Result<ProjectUser>
  fun deleteByProjectIdAndUserId(projectId: UUID, userId: UUID): Result<Unit>
  fun deleteAllProjectUserByProjectId(projectId: UUID): Result<Unit>

  fun deleteAll(): Result<Unit>
}
