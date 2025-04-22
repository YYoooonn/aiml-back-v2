package org.aiml.geometry.infra.persistence.mesh.adapter

import org.aiml.geometry.domain.mesh.model.Material
import org.aiml.geometry.domain.mesh.port.outbound.MaterialPersistencePort
import org.aiml.geometry.infra.persistence.mesh.entity.MaterialEntity
import org.aiml.geometry.infra.persistence.mesh.repository.MaterialRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class MaterialPersistenceAdapter(
  private val materialRepository: MaterialRepository
) : MaterialPersistencePort {
  override fun save(material: Material): Result<Material> = runCatching {
    materialRepository.save(MaterialEntity.from(material)).toDomain()
  }

  override fun findById(id: UUID): Result<Material> = runCatching {
    materialRepository.findById(id).orElseThrow().toDomain()
  }

  override fun findAll(): Result<List<Material>> = runCatching {
    materialRepository.findAll().map { it.toDomain() }
  }

  override fun deleteById(id: UUID): Result<Unit> = runCatching {
    materialRepository.deleteById(id)
  }
}
