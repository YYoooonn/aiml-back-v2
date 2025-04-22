package org.aiml.geometry.infra.persistence.geometry.repository

import org.aiml.geometry.infra.persistence.geometry.entity.FaceEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FaceRepository : JpaRepository<FaceEntity, Long> {
  fun findByGeometryId(geometryId: UUID): Optional<List<FaceEntity>>
  fun deleteByGeometryId(geometryId: UUID)
}
