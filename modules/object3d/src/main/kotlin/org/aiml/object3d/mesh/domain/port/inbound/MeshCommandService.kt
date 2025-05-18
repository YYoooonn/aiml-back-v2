package org.aiml.object3d.mesh.domain.port.inbound

import org.aiml.object3d.base.application.dto.MeshDTO
import java.util.UUID

interface MeshCommandService {
  fun create(dto: MeshDTO, parentId: UUID?): MeshDTO
  fun update(dto: MeshDTO, parentId: UUID?): MeshDTO
  fun delete(id: UUID)
  fun deleteAllByIds(ids: List<UUID>)
  fun deleteAll()
}
