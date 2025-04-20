package org.aiml.project_user.infra.persistence

import org.aiml.project_user.domain.model.ProjectUser
import org.aiml.project_user.domain.port.ProjectUserPersistencePort
import org.aiml.project_user.infra.persistence.repository.ProjectUserRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProjectUserPersistenceAdapter(
  private val projectUserRepository: ProjectUserRepository
) : ProjectUserPersistencePort {
  override fun existsByProjectIdAndUserId(projectId: UUID, userId: UUID): Boolean {
    return projectUserRepository.existsByProjectIdAndUserId(projectId, userId)
  }

  override fun findByProjectId(projectId: UUID): Result<List<ProjectUser>> {
    return runCatching {
      projectUserRepository.findAllByProjectId(projectId)
        .map { it.toDomain() }
    }
  }

  override fun findByUserId(userId: UUID): Result<List<ProjectUser>> {
    return runCatching {
      projectUserRepository.findAllByUserId(userId)
        .map { it.toDomain() }
    }
  }

  override fun findByProjectIdAndUserId(projectId: UUID, userId: UUID): Result<ProjectUser> {
    return runCatching {
      projectUserRepository.findByProjectIdAndUserId(projectId, userId)
        .orElseThrow { RuntimeException("Project and user doesn't match") }
        .toDomain()
    }
  }

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

  override fun deleteAllProjectUser(projectId: UUID): Result<Unit> {
    return runCatching {
      projectUserRepository.deleteAllByProjectId(projectId)
    }
  }
}
