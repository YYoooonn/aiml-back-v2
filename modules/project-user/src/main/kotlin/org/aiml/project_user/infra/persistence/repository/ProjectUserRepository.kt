package org.aiml.project_user.infra.persistence.repository

import jakarta.transaction.Transactional
import org.aiml.project_user.infra.persistence.ProjectUserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import java.util.*

interface ProjectUserRepository : JpaRepository<ProjectUserEntity, UUID> {
  fun existsByProjectIdAndUserId(projectId: UUID, userId: UUID): Boolean
  fun findAllByUserId(userId: UUID): List<ProjectUserEntity>
  fun findAllByProjectId(projectId: UUID): List<ProjectUserEntity>
  fun findByProjectIdAndUserId(projectId: UUID, userId: UUID): Optional<ProjectUserEntity>
  fun deleteByProjectIdAndUserId(projectId: UUID, userId: UUID)


  fun deleteAllByProjectId(projectId: UUID)
  fun deleteAllByUserId(userId: UUID)
}
