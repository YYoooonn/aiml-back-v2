package org.aiml.geometry.domain.mesh.port.outbound

import org.aiml.geometry.domain.mesh.model.Material
import java.util.UUID

interface MaterialPersistencePort {
  fun save(material: Material): Result<Material>
  fun findById(id: UUID): Result<Material>
  fun findAll(): Result<List<Material>>
  fun deleteById(id: UUID): Result<Unit>
}
