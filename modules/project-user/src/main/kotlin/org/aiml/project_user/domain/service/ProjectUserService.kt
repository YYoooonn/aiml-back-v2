package org.aiml.project_user.domain.service

import org.aiml.project_user.domain.command.ProjectUserCommand
import org.aiml.project_user.domain.model.ProjectUser
import org.aiml.project_user.domain.model.ProjectUserRole
import org.aiml.project_user.domain.port.ProjectUserPersistencePort
import org.aiml.project_user.domain.port.ProjectUserUseCase
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProjectUserService(
  private val projectUserPersistencePort: ProjectUserPersistencePort
) : ProjectUserUseCase {
  override fun create(command: ProjectUserCommand): ProjectUser {
    return projectUserPersistencePort.save(ProjectUser.asOwner(command)).getOrThrow()
  }

  override fun assignRole(command: ProjectUserCommand): ProjectUser {
    return when (command.role) {
      ProjectUserRole.OWNER -> assignOwner(command)
      ProjectUserRole.EDITOR -> assignParticipant(command)
      ProjectUserRole.VIEWER -> assignViewer(command)
    }
  }

  override fun getProjectUser(projectId: UUID, userId: UUID): ProjectUser {
    return projectUserPersistencePort.findByProjectIdAndUserId(projectId, userId).getOrThrow()
  }

  override fun getProjectsByUser(userId: UUID): List<ProjectUser> {
    return projectUserPersistencePort.findByUserId(userId).getOrThrow()
  }

  override fun getUsersByProject(projectId: UUID): List<ProjectUser> {
    return projectUserPersistencePort.findByProjectId(projectId).getOrThrow()
  }

  override fun delete(projectId: UUID, userId: UUID) {
    return projectUserPersistencePort.deleteByProjectIdAndUserId(projectId, userId).getOrThrow()
  }

  override fun deleteAll(projectId: UUID) {
    return projectUserPersistencePort.deleteAllProjectUser(projectId).getOrThrow()
  }

  fun assignOwner(command: ProjectUserCommand): ProjectUser {
    return projectUserPersistencePort.upsert(ProjectUser.asOwner(command)).getOrThrow()
  }

  fun assignParticipant(command: ProjectUserCommand): ProjectUser {
    return projectUserPersistencePort.upsert(ProjectUser.asEditor(command)).getOrThrow()
  }

  fun assignViewer(command: ProjectUserCommand): ProjectUser {
    return projectUserPersistencePort.upsert(ProjectUser.asViewer(command)).getOrThrow()
  }
}
