package org.aiml.geometry.infra.persistence.adapter

import org.aiml.geometry.domain.geometry.model.Face
import org.aiml.geometry.domain.geometry.port.outbound.FacePersistencePort
import org.aiml.geometry.domain.geometry.port.outbound.FaceVertexPersistencePort
import org.aiml.geometry.infra.persistence.entity.FVEntityConstructor
import org.aiml.geometry.infra.persistence.entity.FaceEntity
import org.aiml.geometry.infra.persistence.repository.FaceRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class FacePersistenceAdapter(
  private val faceVertexPersistencePort: FaceVertexPersistencePort,
  private val faceRepository: FaceRepository
) : FacePersistencePort {
  override fun saveAll(faces: List<Face>, geoId: UUID): Result<List<Face>> {
    return runCatching {
      val fEntities = faceRepository
        .saveAll(faces.map { FaceEntity.from(it, geoId) })
        // TODO error handler
        .orElseThrow { RuntimeException("error while saving faces") }
      val faceVertices = faceVertexPersistencePort.saveAll(
        faces.flatMapIndexed { fIndex, face ->
          // 저장된 FaceEntity id 기준 매핑
          val faceId = fEntities[fIndex].id
          face.vertices.mapIndexed { vIndex, faceVertex ->
            // FIXME 현재 entity로 만들어서 넘기고 싶지 않아서 data class 만들어서 넘기는 중 더 좋은 방법 고려
            FVEntityConstructor(faceId, vIndex, faceVertex)
          }
        }
      ).getOrThrow()

      val fvMap = faceVertices.groupBy { it.faceId }
      fEntities.map { it.toDomain(fvMap[it.id] ?: emptyList()) }
    }
  }

  override fun deleteByGeometryId(geoId: UUID): Result<Unit> {
    return runCatching {
      faceVertexPersistencePort.deleteByFaceIds(findFaceIds(geoId))
      faceRepository.deleteByGeometryId(geoId)
    }
  }


  private fun findFaceIds(geoId: UUID): List<Long> {
    return faceRepository.findByGeometryId(geoId)
      .orElseThrow { RuntimeException("error while fetching geometry ids") }
      .map { it.id }
  }


}
