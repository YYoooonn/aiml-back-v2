package org.aiml.object3d.mesh.infra.adapter.persistence

import org.aiml.object3d.mesh.domain.model.geometry.Face
import org.aiml.object3d.mesh.domain.port.outbound.FacePersistencePort
import org.aiml.object3d.mesh.domain.port.outbound.FaceVertexPersistencePort
import org.aiml.object3d.mesh.exception.GeometryUnknownException
import org.aiml.object3d.mesh.infra.persistence.entity.geometry.FVEntityConstructor
import org.aiml.object3d.mesh.infra.persistence.entity.geometry.FaceEntity
import org.aiml.object3d.mesh.infra.persistence.repository.FaceRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class FacePersistenceAdapter(
  private val faceVertexPersistencePort: FaceVertexPersistencePort,
  private val faceRepository: FaceRepository
) : FacePersistencePort {
  override fun saveAll(faces: List<Face>, geoId: UUID): Result<List<Face>> = runCatching {
    val fEntities = faceRepository
      .saveAll(faces.map { FaceEntity.from(it, geoId) })
    // TODO error handler
    if (fEntities.isEmpty()) throw GeometryUnknownException("error while saving faces")
    val faceVertices = faceVertexPersistencePort.saveAll(
      faces.flatMapIndexed { fIndex, face ->
        // 저장된 FaceEntity id 기준 매핑
        val geometryId = fEntities[fIndex].geometryId
        face.vertices.mapIndexed { vIndex, faceVertex ->
          // FIXME 현재 entity로 만들어서 넘기고 싶지 않아서 data class 만들어서 넘기는 중 더 좋은 방법 고려
          FVEntityConstructor(fIndex, geometryId, vIndex, faceVertex)
        }
      }
    ).getOrThrow()

    val fvMap = faceVertices.groupBy { it.faceIndex }
    fEntities.map { it.toDomain(fvMap[it.index] ?: emptyList()) }
  }


  override fun deleteByGeometryId(geoId: UUID): Result<Unit> = runCatching {
    faceVertexPersistencePort.deleteByGeometryIds(listOf(geoId))
    faceRepository.deleteByGeometryId(geoId)
  }

  override fun deleteByGeometryIds(geoIds: List<UUID>): Result<Unit> = runCatching {
    faceVertexPersistencePort.deleteByGeometryIds(geoIds)
    faceRepository.deleteAllByGeometryIdIn(geoIds)
  }


  private fun findFaceIds(geoId: UUID): List<Int> {
    return faceRepository.findAllByGeometryId(geoId)
      .map { it.index }
  }

  override fun deleteAll(): Result<Unit> = runCatching {
    faceRepository.deleteAll()
  }


}
