package org.aiml.geometry.infra.persistence.geometry.repository

import org.aiml.geometry.infra.persistence.geometry.entity.FaceVertexEntity
import org.aiml.geometry.infra.persistence.geometry.entity.FaceVertexId
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FaceVertexRepository : JpaRepository<FaceVertexEntity, FaceVertexId> {
  fun deleteByFaceIdIn(faceIds: List<Long>)
}
