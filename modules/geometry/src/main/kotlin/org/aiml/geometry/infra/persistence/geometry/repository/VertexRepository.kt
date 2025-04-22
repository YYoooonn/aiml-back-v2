package org.aiml.geometry.infra.persistence.geometry.repository

import org.aiml.geometry.infra.persistence.geometry.entity.VertexEntity
import org.aiml.geometry.infra.persistence.geometry.entity.VertexId
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface VertexRepository : JpaRepository<VertexEntity, VertexId> {
  fun deleteByGeometryId(geometryId: UUID)
}
