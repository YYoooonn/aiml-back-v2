package org.aiml.object3d.mesh.infra.adapter.query

import org.aiml.object3d.mesh.domain.model.material.Material
import org.aiml.object3d.mesh.domain.port.outbound.query.MaterialQueryPort
import org.aiml.object3d.mesh.infra.persistence.repository.MaterialRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class MaterialQueryAdapter(
  private val materialRepository: MaterialRepository
) : MaterialQueryPort {
  override fun findByIds(ids: List<UUID>): Result<List<Material>> = runCatching {
    materialRepository.findAllById(ids).map { it.toDomain() }
  }

  override fun findById(id: UUID): Result<Material> = runCatching {
    materialRepository.findById(id).orElseThrow().toDomain()
  }

  override fun findAll(): Result<List<Material>> = runCatching {
    materialRepository.findAll().map { it.toDomain() }
  }
}
