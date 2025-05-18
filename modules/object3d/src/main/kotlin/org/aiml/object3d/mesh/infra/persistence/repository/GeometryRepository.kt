package org.aiml.object3d.mesh.infra.persistence.repository

import org.aiml.object3d.mesh.infra.persistence.entity.geometry.GeometryEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface GeometryRepository : JpaRepository<GeometryEntity, UUID> {
  fun findByMeshId(meshId: UUID): Optional<GeometryEntity>
}
