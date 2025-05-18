package org.aiml.object3d.mesh.domain.port.outbound

import org.aiml.object3d.mesh.domain.model.geometry.FaceVertex
import org.aiml.object3d.mesh.infra.persistence.entity.geometry.FVEntityConstructor
import java.util.*

interface FaceVertexPersistencePort {
  fun saveAll(constructors: List<FVEntityConstructor>): Result<List<FaceVertex>>
  fun deleteByFaceIds(faceIds: List<Long>): Result<Unit>
}
