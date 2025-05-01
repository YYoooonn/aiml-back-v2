package org.aiml.object3d.base.infra.persistence.repository

import org.aiml.object3d.base.infra.persistence.entity.Object3DEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface Object3DRepository : JpaRepository<Object3DEntity, UUID> {
  fun findAllBySceneId(sceneId: UUID): List<Object3DEntity>
  fun deleteAllBySceneId(sceneId: UUID)
}
