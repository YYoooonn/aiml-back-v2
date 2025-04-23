package org.aiml.geometry.infra.persistence.mesh.adapter

import org.aiml.geometry.domain.mesh.model.Mesh
import org.aiml.geometry.domain.mesh.port.outbound.MeshPersistencePort
import org.aiml.geometry.infra.persistence.mesh.entity.MeshEntity
import org.aiml.geometry.infra.persistence.mesh.repository.MeshRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class MeshPersistenceAdapter(
  private val meshRepository: MeshRepository
) : MeshPersistencePort {
  override fun save(mesh: Mesh): Result<Mesh> = runCatching {
    meshRepository.save(MeshEntity.from(mesh)).toDomain()
  }

  override fun findById(id: UUID): Result<Mesh> = runCatching {
    meshRepository.findById(id).orElseThrow().toDomain()
  }

  override fun deleteById(id: UUID): Result<Unit> = runCatching {
    meshRepository.deleteById(id)
  }

  override fun findAll(): Result<List<Mesh>> = runCatching {
    meshRepository.findAll().map { it.toDomain() }
  }
}
