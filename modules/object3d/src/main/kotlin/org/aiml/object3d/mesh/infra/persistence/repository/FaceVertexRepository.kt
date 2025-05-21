package org.aiml.object3d.mesh.infra.persistence.repository

import org.aiml.object3d.mesh.infra.persistence.entity.geometry.FaceVertexEntity
import org.aiml.object3d.mesh.infra.persistence.entity.geometry.FaceVertexId
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FaceVertexRepository : JpaRepository<FaceVertexEntity, FaceVertexId> {
  //  fun deleteByFaceIdIn(faceIds: List<Long>)
  fun deleteByGeometryIdIn(geometryIds: Collection<UUID>)
}
