package org.aiml.geometry.domain.mesh.service

import org.aiml.geometry.domain.mesh.command.*
import org.aiml.geometry.domain.mesh.model.Mesh
import org.aiml.geometry.domain.mesh.port.inbound.MeshUseCase
import org.aiml.geometry.domain.mesh.port.outbound.MeshPersistencePort
import org.aiml.geometry.exception.*
import org.springframework.stereotype.Service
import java.util.*

@Service
class MeshService(
  private val meshPersistencePort: MeshPersistencePort
) : MeshUseCase {
  override fun create(command: CreateMeshCommand) = meshPersistencePort.save(Mesh.build(command))
    .getOrElse { throw GeometryUnknownException("error while creating mesh") }

  override fun deleteById(id: UUID) = meshPersistencePort.deleteById(id)
    .getOrElse { throw GeometryUnknownException("error while deleting mesh") }

  override fun update(command: UpdateMeshCommand): Mesh {
    val mesh = getById(command.id)
    return meshPersistencePort.save(mesh.update(command))
      .getOrElse { throw GeometryUnknownException("error while updating mesh") }
  }

  override fun getById(id: UUID) = meshPersistencePort.findById(id)
    .getOrElse { throw GeometryNotFoundException("error while getting mesh") }

  override fun getAll(): List<Mesh> = meshPersistencePort.findAll().getOrThrow()
}
