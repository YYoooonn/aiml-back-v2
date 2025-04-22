package org.aiml.geometry.infra.persistence.repository

import org.aiml.geometry.infra.persistence.entity.FaceEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface FaceRepository : JpaRepository<FaceEntity, Long> {
  fun saveAll(f: List<FaceEntity>): Optional<List<FaceEntity>>
  fun findByGeometryId(geometryId: UUID): Optional<List<FaceEntity>>
  fun deleteByGeometryId(geometryId: UUID)
}
