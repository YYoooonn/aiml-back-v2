package org.aiml.geometry.domain.geometry.port.outbound

import org.aiml.geometry.domain.geometry.model.Face
import org.aiml.geometry.domain.geometry.model.Geometry
import org.aiml.geometry.domain.geometry.model.Vertex
import java.util.UUID

interface GeometryQueryPort {
  //  fun findVerticesByFaceId(faceId: Long, geometryId: UUID): Result<List<Vertex>>
  fun findById(geometryId: UUID): Result<Geometry>
  fun findFacesByGeometryId(geometryId: UUID): Result<List<Face>>
  fun findVerticesByGeometryId(geometryId: UUID): Result<List<Vertex>>
}
