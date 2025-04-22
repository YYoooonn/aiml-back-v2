package org.aiml.geometry.domain.geometry.port.outbound

import org.aiml.geometry.domain.geometry.model.Face
import java.util.UUID

interface FacePersistencePort {
  fun saveAll(faces: List<Face>, geoId: UUID): Result<List<Face>>
  fun deleteByGeometryId(geoId: UUID): Result<Unit>
}
