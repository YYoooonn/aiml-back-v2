package org.aiml.object3d.mesh.infra.adapter.persistence

import org.aiml.object3d.mesh.domain.model.Mesh
import org.aiml.object3d.mesh.domain.port.outbound.command.MeshCommandPort
import org.aiml.object3d.base.domain.port.outbound.Object3DQueryPort
import org.aiml.object3d.base.infra.adapter.mapper.Object3DEntityMapper
import org.aiml.object3d.mesh.infra.persistence.entity.MeshEntity
import org.aiml.object3d.mesh.infra.persistence.repository.MeshRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class MeshCommandAdapter(
  private val meshRepository: MeshRepository,
  private val object3DQueryPort: Object3DQueryPort,
  private val objectMapper: Object3DEntityMapper
) : MeshCommandPort {

  override fun save(mesh: Mesh): Result<Mesh> = runCatching {
    saveAll(listOf(mesh))
      .getOrThrow()
      .firstOrNull()
      ?: throw NoSuchElementException("Mesh not saved")
  }

  override fun update(mesh: Mesh): Result<Mesh> = runCatching {
    val parent = mesh.parentId?.let { object3DQueryPort.findById(it).getOrThrow() }
    val entity = meshRepository.save(MeshEntity.from(mesh, parent?.let { objectMapper.toEntityRef(it) }))
    entity.toDomain()
  }

  override fun saveAll(meshes: List<Mesh>): Result<List<Mesh>> = runCatching {
    if (meshes.isEmpty()) return@runCatching emptyList()

    val parentEntities = object3DQueryPort.findAllByIds(
      meshes.mapNotNull { it.parentId }.distinct()
    ).getOrThrow().associateBy { it.id }

    // (2) 저장
    val savedEntities = meshRepository.saveAllAndFlush(
      meshes.map { mesh ->
        val parentEntity = mesh.parentId?.let { pId ->
          parentEntities[pId]?.let { objectMapper.toEntityRef(it) }
        }
        MeshEntity.from(mesh, parentEntity)
      }
    )

    // (3) Entity.id로 원래 Mesh 찾아서 도메인 만들기
    savedEntities.map { it.toDomain() }
  }

  override fun deleteById(id: UUID): Result<Unit> = runCatching {
    meshRepository.deleteById(id)
  }

  override fun deleteByIds(ids: List<UUID>): Result<Unit> = runCatching {
    meshRepository.deleteAllById(ids)
  }


  override fun deleteAll(): Result<Unit> = runCatching {
    meshRepository.deleteAll()
  }
}
