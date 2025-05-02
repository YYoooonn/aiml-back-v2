package org.aiml.object3d.mesh.domain.port.outbound.command

import org.aiml.object3d.mesh.domain.model.Mesh
import java.util.UUID

interface MeshCommandPort {
  fun save(mesh: Mesh): Result<Mesh>
  fun update(mesh: Mesh): Result<Mesh>
  fun saveAll(meshes: List<Mesh>): Result<List<Mesh>>
  fun deleteById(id: UUID): Result<Unit>

  fun deleteByIds(ids: List<UUID>): Result<Unit>

  fun deleteAll(): Result<Unit>
}
