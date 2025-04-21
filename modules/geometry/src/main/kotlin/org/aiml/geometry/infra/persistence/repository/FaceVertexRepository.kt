package org.aiml.geometry.infra.persistence.repository

import org.aiml.geometry.infra.persistence.entity.FaceVertexEntity
import org.aiml.geometry.infra.persistence.entity.FaceVertexId
import org.springframework.data.jpa.repository.JpaRepository

interface FaceVertexJpaRepository : JpaRepository<FaceVertexEntity, FaceVertexId> {
  fun findByFaceId(faceId: Long): List<FaceVertexEntity>
  fun findByFaceIdOrderByVertexIndexOrder(faceId: Long): List<FaceVertexEntity>
  fun deleteByFaceId(faceId: Long)
  fun existsByFaceIdAndVertexIndexOrder(faceId: Long, vertexIndexOrder: Int): Boolean
}
