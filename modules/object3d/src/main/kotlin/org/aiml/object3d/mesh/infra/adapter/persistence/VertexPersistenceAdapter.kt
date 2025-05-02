package org.aiml.object3d.mesh.infra.adapter.persistence

import org.aiml.object3d.mesh.domain.model.geometry.Vertex
import org.aiml.object3d.mesh.domain.port.outbound.VertexPersistencePort
import org.aiml.object3d.mesh.infra.persistence.entity.geometry.VertexEntity
import org.aiml.object3d.mesh.infra.persistence.repository.VertexRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class VertexPersistenceAdapter(
  private val vertexRepository: VertexRepository
) : VertexPersistencePort {
  override fun saveAll(vertices: List<Vertex>, geoId: UUID): Result<List<Vertex>> {
    return runCatching {
      vertexRepository
        .saveAll(vertices.map { VertexEntity.from(it, geoId) })
        .map { it.toDomain() }
    }
  }

  override fun deleteByGeometryId(geometryId: UUID): Result<Unit> {
    return runCatching { vertexRepository.deleteByGeometryId(geometryId) }
  }

  override fun deleteByGeometryIds(geometryIds: List<UUID>): Result<Unit> = runCatching {
    vertexRepository.deleteAllByGeometryIdIn(geometryIds)
  }

  override fun deleteAll(): Result<Unit> = runCatching {
    vertexRepository.deleteAll()
  }
}
