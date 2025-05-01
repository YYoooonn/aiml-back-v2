package org.aiml.object3d.mesh.infra.persistence.repository

import org.aiml.object3d.mesh.infra.persistence.entity.geometry.FaceEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FaceRepository : JpaRepository<FaceEntity, Long> {
  fun findAllByGeometryId(geometryId: UUID): List<FaceEntity>
  fun findAllByGeometryIdIn(geometryIds: List<UUID>): List<FaceEntity>
  fun deleteByGeometryId(geometryId: UUID)
  fun deleteAllByGeometryIdIn(geometryIds: List<UUID>)
}
