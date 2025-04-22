package org.aiml.geometry.infra.persistence.repository

import org.aiml.geometry.infra.persistence.entity.FaceVertexEntity
import org.aiml.geometry.infra.persistence.entity.FaceVertexId
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FaceVertexRepository : JpaRepository<FaceVertexEntity, FaceVertexId> {
  fun saveAll(entities: List<FaceVertexEntity>): Optional<List<FaceVertexEntity>>
  fun deleteByFaceIdIn(faceIds: List<Long>)
}
