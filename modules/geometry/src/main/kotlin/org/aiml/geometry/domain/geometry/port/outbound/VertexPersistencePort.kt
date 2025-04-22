package org.aiml.geometry.domain.geometry.port.outbound

import org.aiml.geometry.domain.geometry.model.Vertex
import java.util.UUID

interface VertexPersistencePort {
  fun saveAll(vertices: List<Vertex>, geoId: UUID): Result<List<Vertex>>
  fun deleteByGeometryId(geometryId: UUID): Result<Unit>
}
