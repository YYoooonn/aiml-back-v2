package org.aiml.geometry.infra.persistence.repository

import org.aiml.geometry.infra.persistence.entity.FaceVertexEntity
import org.aiml.geometry.infra.persistence.entity.FaceVertexId
import org.springframework.data.jpa.repository.JpaRepository

interface FaceVertexJpaRepository :
  JpaRepository<org.aiml.geometry.infra.persistence.entity.FaceVertexEntity, org.aiml.geometry.infra.persistence.entity.FaceVertexId> {
  fun findByFaceId(faceId: Long): List<org.aiml.geometry.infra.persistence.entity.FaceVertexEntity>
  fun findByFaceIdOrderByVertexIndexOrder(faceId: Long): List<org.aiml.geometry.infra.persistence.entity.FaceVertexEntity>
  fun deleteByFaceId(faceId: Long)
  fun existsByFaceIdAndVertexIndexOrder(faceId: Long, vertexIndexOrder: Int): Boolean
}
