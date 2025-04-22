package org.aiml.geometry.infra.persistence.mesh.adapter

import org.aiml.geometry.domain.mesh.model.MeshGroup
import org.aiml.geometry.domain.mesh.port.outbound.MeshGroupPersistencePort
import org.aiml.geometry.domain.mesh.port.outbound.MeshPersistencePort
import org.aiml.geometry.infra.persistence.mesh.entity.MeshGroupEntity
import org.aiml.geometry.infra.persistence.mesh.repository.MeshGroupRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class MeshGroupAdapter(
  private val meshGroupRepository: MeshGroupRepository,
  private val meshPersistencePort: MeshPersistencePort
) : MeshGroupPersistencePort {

  override fun save(meshGroup: MeshGroup): Result<MeshGroup> = runCatching {
    val entity = meshGroupRepository.save(MeshGroupEntity.from(meshGroup))
    val meshes = meshGroup.meshes.map { meshPersistencePort.save(it).getOrThrow() }
    entity.toDomain(meshes)
  }

  override fun findById(id: UUID): Result<MeshGroup> = runCatching {
    val entity = meshGroupRepository.findById(id).orElseThrow()
    val meshes = entity.meshes.map { it.toDomain() }
    entity.toDomain(meshes)
  }

  override fun findAll(): Result<List<MeshGroup>> = runCatching {
    meshGroupRepository.findAll().map { entity ->
      val meshes = entity.meshes.map { it.toDomain() }
      entity.toDomain(meshes)
    }
  }

  override fun deleteById(id: UUID): Result<Unit> = runCatching {
    meshGroupRepository.deleteById(id)
  }
}
