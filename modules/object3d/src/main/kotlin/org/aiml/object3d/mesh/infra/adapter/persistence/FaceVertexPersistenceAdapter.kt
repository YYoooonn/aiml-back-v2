package org.aiml.object3d.mesh.infra.adapter.persistence

import org.aiml.object3d.mesh.domain.model.geometry.FaceVertex
import org.aiml.object3d.mesh.domain.port.outbound.FaceVertexPersistencePort
import org.aiml.object3d.mesh.exception.GeometryUnknownException
import org.aiml.object3d.mesh.infra.persistence.entity.geometry.FVEntityConstructor
import org.aiml.object3d.mesh.infra.persistence.entity.geometry.FaceVertexEntity
import org.aiml.object3d.mesh.infra.persistence.repository.FaceVertexRepository
import org.springframework.stereotype.Component
import java.util.*

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

  override fun deleteByGeometryIds(geometryIds: List<UUID>): Result<Unit> = runCatching {
    faceVertexRepository.deleteByGeometryIdIn(geometryIds)
  }
  
//
//  override fun deleteByFaceIds(faceIds: List<Long>): Result<Unit> = runCatching {
//    TODO()
//  }

}
