package org.aiml.geometry.domain.mesh.port.outbound

import org.aiml.geometry.domain.mesh.model.MeshGroup
import java.util.*

interface MeshGroupPersistencePort {
  fun save(meshGroup: MeshGroup): Result<MeshGroup>
  fun findById(id: UUID): Result<MeshGroup>
  fun findAll(): Result<List<MeshGroup>>
  fun deleteById(id: UUID): Result<Unit>
}
