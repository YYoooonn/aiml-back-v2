package org.aiml.geometry.infra.persistence.repository

import org.aiml.geometry.infra.persistence.entity.VertexEntity
import org.aiml.geometry.infra.persistence.entity.VertexId
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface VertexRepository : JpaRepository<VertexEntity, VertexId> {
  fun deleteByGeometryId(geometryId: UUID)
}
