package org.aiml.geometry.infra.persistence.adapter

import org.aiml.geometry.domain.geometry.model.FaceVertex
import org.aiml.geometry.domain.geometry.port.outbound.FaceVertexPersistencePort
import org.aiml.geometry.infra.persistence.entity.FVEntityConstructor
import org.aiml.geometry.infra.persistence.entity.FaceVertexEntity
import org.aiml.geometry.infra.persistence.repository.FaceVertexRepository
import org.springframework.stereotype.Component

@Component
class FaceVertexPersistenceAdapter(
  private val faceVertexRepository: FaceVertexRepository
) : FaceVertexPersistencePort {
  override fun saveAll(constructors: List<FVEntityConstructor>): Result<List<FaceVertex>> {
    return runCatching {
      faceVertexRepository
        .saveAll(constructors.map { FaceVertexEntity.from(it) })
        // TODO error handler
        .orElseThrow { RuntimeException("error while saving face vertex entities") }
        .map { it.toDomain() }
    }
  }

  override fun deleteByFaceIds(faceIds: List<Long>): Result<Unit> {
    return runCatching {
      faceVertexRepository.deleteByFaceIdIn(faceIds)
    }
  }

}
