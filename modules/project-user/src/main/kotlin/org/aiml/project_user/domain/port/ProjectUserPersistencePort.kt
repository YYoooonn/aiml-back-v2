package org.aiml.project_user.domain.port

import org.aiml.project_user.domain.model.ProjectUser
import java.util.UUID

interface ProjectUserPersistencePort {
  fun save(projectUser: ProjectUser): Result<ProjectUser>
  fun upsert(projectUser: ProjectUser): Result<ProjectUser>
  fun existsByProjectIdAndUserId(projectId: UUID, userId: UUID): Boolean
  fun findByProjectId(projectId: UUID): Result<List<ProjectUser>>
  fun findByUserId(userId: UUID): Result<List<ProjectUser>>
  fun findByProjectIdAndUserId(projectId: UUID, userId: UUID): Result<ProjectUser>
  fun deleteByProjectIdAndUserId(projectId: UUID, userId: UUID): Result<Unit>
  fun deleteAllProjectUser(projectId: UUID): Result<Unit>
}
