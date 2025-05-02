package org.aiml.object3d.mesh.infra.adapter.persistence

import org.aiml.object3d.mesh.domain.model.material.Material
import org.aiml.object3d.mesh.domain.port.outbound.command.MaterialCommandPort
import org.aiml.object3d.mesh.infra.persistence.repository.MaterialRepository
import org.aiml.object3d.mesh.infra.persistence.entity.MaterialEntity
import org.springframework.stereotype.Component
import java.util.*

@Component
class MaterialCommandAdapter(
  private val materialRepository: MaterialRepository
) : MaterialCommandPort {
  override fun save(material: Material): Result<Material> = runCatching {
    materialRepository.save(MaterialEntity.from(material)).toDomain()
  }

  override fun update(material: Material): Result<Material> = runCatching {
    materialRepository.save(MaterialEntity.from(material)).toDomain()
  }

  override fun deleteById(id: UUID): Result<Unit> = runCatching {
    materialRepository.deleteById(id)
  }

  override fun deleteByMeshId(id: UUID): Result<Unit> = runCatching {
    materialRepository.deleteByMeshId(id)
  }

  override fun deleteAllByMeshIds(meshIds: List<UUID>): Result<Unit> = runCatching {
    materialRepository.deleteAllByMeshIdIn(meshIds)
  }

  override fun deleteAll(): Result<Unit> = runCatching {
    materialRepository.deleteAll()
  }

}
