package org.aiml.project_user.domain.port.outbound

import org.aiml.project_user.domain.model.ProjectUser
import java.util.*

interface ProjectUserQueryPort {
  fun existsByProjectIdAndUserId(projectId: UUID, userId: UUID): Boolean
  fun findByProjectId(projectId: UUID): Result<List<ProjectUser>>
  fun findByUserId(userId: UUID): Result<List<ProjectUser>>
  fun findByProjectIdAndUserId(projectId: UUID, userId: UUID): Result<ProjectUser>

  fun findAll(): Result<List<ProjectUser>>
}
