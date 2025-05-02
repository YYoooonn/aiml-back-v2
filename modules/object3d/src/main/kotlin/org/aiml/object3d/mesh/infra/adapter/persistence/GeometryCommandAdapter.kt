package org.aiml.object3d.mesh.infra.adapter.persistence

import jakarta.transaction.Transactional
import org.aiml.object3d.mesh.domain.port.outbound.VertexPersistencePort
import org.aiml.object3d.mesh.domain.model.geometry.Geometry
import org.aiml.object3d.mesh.domain.port.outbound.FacePersistencePort
import org.aiml.object3d.mesh.domain.port.outbound.command.GeometryCommandPort
import org.aiml.object3d.mesh.infra.persistence.entity.geometry.GeometryEntity
import org.aiml.object3d.mesh.infra.persistence.repository.GeometryRepository
import org.springframework.stereotype.Component
import java.util.*
import kotlin.jvm.optionals.getOrElse

@Transactional
@Component
class GeometryCommandAdapter(
  private val geometryRepository: GeometryRepository,
  private val facePersistencePort: FacePersistencePort,
  private val vertexPersistencePort: VertexPersistencePort,
) : GeometryCommandPort {

  override fun save(geometry: Geometry): Geometry {
    val geoEntity = geometryRepository.save(GeometryEntity.from(geometry))
    val vertices = vertexPersistencePort.saveAll(geometry.vertices, geoEntity.id).getOrThrow()
    val faces = facePersistencePort.saveAll(geometry.faces, geoEntity.id).getOrThrow()
    return geoEntity.toDomain(vertices, faces)
  }

  override fun update(geometry: Geometry): Geometry {
    return save(geometry)
  }

  override fun deleteById(id: UUID) {
    facePersistencePort.deleteByGeometryId(id).getOrThrow()
    vertexPersistencePort.deleteByGeometryId(id).getOrThrow()
    return geometryRepository.deleteById(id)
  }

  override fun deleteByMeshId(id: UUID) {
    val entity = geometryRepository.findByMeshId(id)
      .getOrElse { throw RuntimeException("no geometry found for meshId $id") }
    return deleteById(entity.id)
  }

  override fun deleteAllByMeshIds(ids: List<UUID>) {
    TODO("Not yet implemented")
  }

  override fun deleteAll() {
    geometryRepository.deleteAll()
    vertexPersistencePort.deleteAll()
    facePersistencePort.deleteAll()
  }

}
