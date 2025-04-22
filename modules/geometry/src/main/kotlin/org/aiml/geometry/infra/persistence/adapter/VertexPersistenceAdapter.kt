package org.aiml.geometry.infra.persistence.adapter

import org.aiml.geometry.domain.geometry.model.Vertex
import org.aiml.geometry.domain.geometry.port.outbound.VertexPersistencePort
import org.aiml.geometry.infra.persistence.entity.VertexEntity
import org.aiml.geometry.infra.persistence.repository.VertexRepository
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
}
