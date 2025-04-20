package org.aiml.project_user.domain.port

import org.aiml.project_user.domain.command.ProjectUserCommand
import org.aiml.project_user.domain.model.ProjectUser
import java.util.UUID

interface ProjectUserUseCase {
  fun create(command: ProjectUserCommand): ProjectUser
  fun assignRole(command: ProjectUserCommand): ProjectUser
  fun getProjectUser(projectId: UUID, userId: UUID): ProjectUser
  fun getUsersByProject(projectId: UUID): List<ProjectUser>
  fun getProjectsByUser(userId: UUID): List<ProjectUser>
  fun delete(projectId: UUID, userId: UUID)
  fun deleteAll(projectId: UUID)
}
