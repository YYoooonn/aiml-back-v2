package org.aiml.object3d.mesh.infra.persistence.repository

import org.aiml.object3d.mesh.infra.persistence.entity.MaterialEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MaterialRepository : JpaRepository<MaterialEntity, UUID> {
  fun deleteByMeshId(meshId: UUID)
  fun deleteAllByMeshIdIn(meshIds: List<UUID>)
}
