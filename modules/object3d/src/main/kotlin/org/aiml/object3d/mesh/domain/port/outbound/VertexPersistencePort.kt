package org.aiml.object3d.mesh.domain.port.outbound

import org.aiml.object3d.mesh.domain.model.geometry.Vertex
import java.util.UUID

interface VertexPersistencePort {
  fun saveAll(vertices: List<Vertex>, geoId: UUID): Result<List<Vertex>>
  fun deleteByGeometryId(geometryId: UUID): Result<Unit>
  fun deleteByGeometryIds(geometryIds: List<UUID>): Result<Unit>
}
