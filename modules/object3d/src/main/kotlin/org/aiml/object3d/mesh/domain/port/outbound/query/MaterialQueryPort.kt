package org.aiml.object3d.mesh.domain.port.outbound.query

import org.aiml.object3d.mesh.domain.model.material.Material
import java.util.*

interface MaterialQueryPort {
  fun findByIds(ids: List<UUID>): Result<List<Material>>
  fun findById(id: UUID): Result<Material>
  fun findAll(): Result<List<Material>>
}
