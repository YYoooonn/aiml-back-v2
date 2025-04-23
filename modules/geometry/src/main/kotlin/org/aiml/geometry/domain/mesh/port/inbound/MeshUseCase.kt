package org.aiml.geometry.domain.mesh.port.inbound

import org.aiml.geometry.domain.mesh.command.*
import org.aiml.geometry.domain.mesh.model.Mesh
import java.util.*

interface MeshUseCase {
  fun create(command: CreateMeshCommand): Mesh
  fun update(command: UpdateMeshCommand): Mesh
  fun getById(id: UUID): Mesh
  fun deleteById(id: UUID)

  fun getAll(): List<Mesh>
}
