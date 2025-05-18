package org.aiml.object3d.mesh.domain.port.outbound.query

import org.aiml.object3d.mesh.domain.model.geometry.Geometry
import java.util.UUID

interface GeometryQueryPort {
  //  fun findVerticesByFaceId(faceId: Long, geometryId: UUID): Result<List<Vertex>>
  fun findByIds(ids: List<UUID>): Result<List<Geometry>>
  fun findById(geometryId: UUID): Result<Geometry>
}
