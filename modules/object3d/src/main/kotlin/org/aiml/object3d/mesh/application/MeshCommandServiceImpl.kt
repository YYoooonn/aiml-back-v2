package org.aiml.object3d.mesh.application

import jakarta.transaction.Transactional
import org.aiml.object3d.base.application.dto.GeometryDTO
import org.aiml.object3d.base.application.dto.MaterialDTO
import org.aiml.object3d.base.application.dto.MeshDTO
import org.aiml.object3d.mesh.domain.port.inbound.MeshCommandService
import org.aiml.object3d.mesh.domain.port.outbound.command.GeometryCommandPort
import org.aiml.object3d.mesh.domain.port.outbound.command.MaterialCommandPort
import org.aiml.object3d.mesh.domain.port.outbound.command.MeshCommandPort
import org.springframework.stereotype.Service
import java.util.*

@Transactional
@Service
class MeshCommandServiceImpl(
  private val meshCommandPort: MeshCommandPort,
  private val geometryCommandPort: GeometryCommandPort,
  private val materialCommandPort: MaterialCommandPort
) : MeshCommandService {

  override fun create(dto: MeshDTO, parentId: UUID?): MeshDTO {
    // initialize for meshId
    val mesh = dto.toDomain(parentId)
    val geo = geometryCommandPort.save(dto.geometry.toDomain(mesh.id))
    val mat = materialCommandPort.save(dto.material.toDomain(mesh.id)).getOrThrow()

    val new = meshCommandPort.save(mesh.copy(geometryId = geo.id, materialId = mat.id))
      .getOrThrow()
    return MeshDTO.from(new, dto.geometry, dto.material)
  }

  override fun delete(id: UUID) {
    geometryCommandPort.deleteByMeshId(id)
    materialCommandPort.deleteByMeshId(id).getOrThrow()
    return meshCommandPort.deleteById(id).getOrThrow()
  }

  override fun update(dto: MeshDTO, parentId: UUID?): MeshDTO {
    if (dto.id == null) throw RuntimeException("must provide id for deletion")
    val geo = geometryCommandPort.update(dto.geometry.toDomain(dto.id))
    val mat = materialCommandPort.update(dto.material.toDomain(dto.id)).getOrThrow()
    val updated = meshCommandPort.update(dto.toDomain(parentId)).getOrThrow()
    return MeshDTO.from(updated, GeometryDTO.from(geo), MaterialDTO.from(mat))
  }

  override fun deleteAllByIds(ids: List<UUID>) {
    geometryCommandPort.deleteAllByMeshIds(ids)
    materialCommandPort.deleteAllByMeshIds(ids).getOrThrow()
    return meshCommandPort.deleteByIds(ids).getOrThrow()
  }

  override fun deleteAll() {
    geometryCommandPort.deleteAll()
    materialCommandPort.deleteAll().getOrThrow()
    return meshCommandPort.deleteAll().getOrThrow()
  }

}
