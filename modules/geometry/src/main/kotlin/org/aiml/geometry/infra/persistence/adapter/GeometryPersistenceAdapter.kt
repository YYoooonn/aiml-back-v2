package org.aiml.geometry.infra.persistence.adapter

import jakarta.transaction.Transactional
import org.aiml.geometry.domain.geometry.port.outbound.VertexPersistencePort
import org.aiml.geometry.domain.geometry.model.Geometry
import org.aiml.geometry.domain.geometry.port.outbound.FacePersistencePort
import org.aiml.geometry.domain.geometry.port.outbound.GeometryPersistencePort
import org.aiml.geometry.domain.geometry.port.outbound.GeometryQueryPort
import org.aiml.geometry.infra.persistence.entity.GeometryEntity
import org.aiml.geometry.infra.persistence.repository.GeometryRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class GeometryPersistenceAdapter(
  private val geometryRepository: GeometryRepository,
  private val facePersistencePort: FacePersistencePort,
  private val vertexPersistencePort: VertexPersistencePort,
  private val geometryQueryPort: GeometryQueryPort
) : GeometryPersistencePort {

  @Transactional
  override fun save(geometry: Geometry): Result<Geometry> {
    return runCatching {
      val geoEntity = geometryRepository.save(GeometryEntity.from(geometry))
      val vertices = vertexPersistencePort.saveAll(geometry.vertices, geoEntity.id).getOrThrow()
      val faces = facePersistencePort.saveAll(geometry.faces, geoEntity.id).getOrThrow()

      geoEntity.toDomain(vertices, faces)
    }
  }

  override fun findById(id: UUID): Result<Geometry> {
    return runCatching {
      geometryQueryPort.findById(id).getOrThrow()
    }
  }

  @Transactional
  override fun deleteById(id: UUID): Result<Unit> {
    return runCatching {
      facePersistencePort.deleteByGeometryId(id).getOrThrow()
      vertexPersistencePort.deleteByGeometryId(id).getOrThrow()
      geometryRepository.deleteById(id)
    }
  }

}
