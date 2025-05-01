package org.aiml.object3d.mesh.infra.persistence.repository

import org.aiml.object3d.mesh.infra.persistence.entity.geometry.VertexEntity
import org.aiml.object3d.mesh.infra.persistence.entity.geometry.VertexId
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface VertexRepository : JpaRepository<VertexEntity, VertexId> {
  fun deleteByGeometryId(geometryId: UUID)
  fun deleteAllByGeometryIdIn(geometryIds: List<UUID>)
}
