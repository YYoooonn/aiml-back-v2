package org.aiml.object3d.mesh.domain.port.outbound

import org.aiml.object3d.mesh.domain.model.geometry.Face
import java.util.UUID

interface FacePersistencePort {
  fun saveAll(faces: List<Face>, geoId: UUID): Result<List<Face>>
  fun deleteByGeometryId(geoId: UUID): Result<Unit>
  fun deleteByGeometryIds(geoIds: List<UUID>): Result<Unit>

  fun deleteAll(): Result<Unit>
}
