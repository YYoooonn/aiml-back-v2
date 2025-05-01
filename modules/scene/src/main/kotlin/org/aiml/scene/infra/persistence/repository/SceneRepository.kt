package org.aiml.scene.infra.persistence.repository

import org.aiml.scene.infra.persistence.entity.SceneEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SceneRepository : JpaRepository<SceneEntity, UUID> {
  fun findAllByProjectId(projectId: UUID): List<SceneEntity>
  fun deleteAllByProjectId(projectId: UUID)
}
