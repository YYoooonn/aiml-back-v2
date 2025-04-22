package org.aiml.geometry.domain.mesh.port.outbound

import org.aiml.geometry.domain.mesh.model.Mesh
import java.util.UUID

interface MeshPersistencePort {
  fun save(mesh: Mesh): Result<Mesh>
  fun findById(id: UUID): Result<Mesh>
  fun deleteById(id: UUID): Result<Unit>
}
