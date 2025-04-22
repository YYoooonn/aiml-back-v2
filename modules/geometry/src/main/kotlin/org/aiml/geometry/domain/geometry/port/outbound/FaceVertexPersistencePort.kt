package org.aiml.geometry.domain.geometry.port.outbound

import org.aiml.geometry.domain.geometry.model.FaceVertex
import org.aiml.geometry.infra.persistence.geometry.entity.FVEntityConstructor

interface FaceVertexPersistencePort {
  fun saveAll(constructors: List<FVEntityConstructor>): Result<List<FaceVertex>>
  fun deleteByFaceIds(faceIds: List<Long>): Result<Unit>
}
