package org.aiml.project_user.infra.adapter

import org.aiml.project_user.domain.model.ProjectUser
import org.aiml.project_user.domain.port.outbound.ProjectUserQueryPort
import org.aiml.project_user.infra.persistence.repository.ProjectUserRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProjectUserQueryAdapter(
  private val projectUserRepository: ProjectUserRepository
) : ProjectUserQueryPort {
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

  override fun findAll(): Result<List<ProjectUser>> = runCatching {
    projectUserRepository.findAll().map { it.toDomain() }
  }
}
