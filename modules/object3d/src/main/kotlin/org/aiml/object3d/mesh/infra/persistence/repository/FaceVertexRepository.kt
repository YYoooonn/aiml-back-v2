package org.aiml.object3d.mesh.infra.persistence.repository

import org.aiml.object3d.mesh.infra.persistence.entity.geometry.FaceVertexEntity
import org.aiml.object3d.mesh.infra.persistence.entity.geometry.FaceVertexId
import org.springframework.data.jpa.repository.JpaRepository

interface FaceVertexRepository : JpaRepository<FaceVertexEntity, FaceVertexId> {
  fun deleteByFaceIdIn(faceIds: List<Long>)
}
