package org.aiml.object3d.mesh.domain.port.outbound.command

import org.aiml.object3d.mesh.domain.model.material.Material
import java.util.*

interface MaterialCommandPort {
  fun save(material: Material): Result<Material>
  fun update(material: Material): Result<Material>
  fun deleteById(id: UUID): Result<Unit>
  fun deleteByMeshId(id: UUID): Result<Unit>

  fun deleteAllByMeshIds(meshIds: List<UUID>): Result<Unit>
}
