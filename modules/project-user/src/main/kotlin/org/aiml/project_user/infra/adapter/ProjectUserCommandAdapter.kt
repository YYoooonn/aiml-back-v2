package org.aiml.project_user.infra.adapter

import org.aiml.project_user.domain.model.ProjectUser
import org.aiml.project_user.domain.port.outbound.ProjectUserCommandPort
import org.aiml.project_user.infra.persistence.ProjectUserEntity
import org.aiml.project_user.infra.persistence.repository.ProjectUserRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProjectUserCommandAdapter(
  private val projectUserRepository: ProjectUserRepository
) : ProjectUserCommandPort {

  override fun save(projectUser: ProjectUser): Result<ProjectUser> {
    return runCatching {
      projectUserRepository.save(ProjectUserEntity.from(projectUser))
        .toDomain()
    }
  }

  override fun upsert(projectUser: ProjectUser): Result<ProjectUser> {
    val existing = projectUserRepository.findByProjectIdAndUserId(projectUser.projectId, projectUser.userId)
      .orElse(null)
    return runCatching {
      if (existing != null) {
        val update = existing.copy(role = projectUser.role)
        projectUserRepository.save(update).toDomain()
      } else {
        val entity = ProjectUserEntity.from(projectUser)
        projectUserRepository.save(entity).toDomain()
      }
    }
  }

  override fun deleteByProjectIdAndUserId(projectId: UUID, userId: UUID): Result<Unit> {
    return runCatching {
      projectUserRepository.deleteByProjectIdAndUserId(projectId, userId)
    }
  }

  override fun deleteAllProjectUserByProjectId(projectId: UUID): Result<Unit> {
    return runCatching {
      projectUserRepository.deleteAllByProjectId(projectId)
    }
  }

  override fun deleteAllByUserId(userId: UUID): Result<Unit> {
    return runCatching {
      projectUserRepository.deleteAllByUserId(userId)
    }
  }

  override fun deleteAll(): Result<Unit> = runCatching {
    projectUserRepository.deleteAll()
  }
}
