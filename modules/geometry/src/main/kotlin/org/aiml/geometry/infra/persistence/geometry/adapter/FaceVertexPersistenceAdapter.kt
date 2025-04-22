package org.aiml.geometry.infra.persistence.geometry.adapter

import org.aiml.geometry.domain.geometry.model.FaceVertex
import org.aiml.geometry.domain.geometry.port.outbound.FaceVertexPersistencePort
import org.aiml.geometry.exception.GeometryUnknownException
import org.aiml.geometry.infra.persistence.geometry.entity.FVEntityConstructor
import org.aiml.geometry.infra.persistence.geometry.entity.FaceVertexEntity
import org.aiml.geometry.infra.persistence.geometry.repository.FaceVertexRepository
import org.springframework.stereotype.Component

@Component
class FaceVertexPersistenceAdapter(
  private val faceVertexRepository: FaceVertexRepository
) : FaceVertexPersistencePort {
  override fun saveAll(constructors: List<FVEntityConstructor>): Result<List<FaceVertex>> = runCatching {
    val saved = faceVertexRepository
      .saveAll(constructors.map { FaceVertexEntity.from(it) })
    // TODO error handler
    if (saved.isEmpty()) throw GeometryUnknownException("error while saving face vertex")
    saved.map { it.toDomain() }
  }


  override fun deleteByFaceIds(faceIds: List<Long>): Result<Unit> {
    return runCatching {
      faceVertexRepository.deleteByFaceIdIn(faceIds)
    }
  }

}
