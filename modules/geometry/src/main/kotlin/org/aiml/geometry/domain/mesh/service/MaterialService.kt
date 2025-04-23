package org.aiml.geometry.domain.mesh.service

import org.aiml.geometry.domain.mesh.command.CreateMaterialCommand
import org.aiml.geometry.domain.mesh.command.UpdateMaterialCommand
import org.aiml.geometry.domain.mesh.model.Material
import org.aiml.geometry.domain.mesh.port.inbound.MaterialUseCase
import org.aiml.geometry.domain.mesh.port.outbound.MaterialPersistencePort
import org.aiml.geometry.exception.*
import org.springframework.stereotype.Service
import java.util.*

@Service
class MaterialService(
  private val materialPersistencePort: MaterialPersistencePort
) : MaterialUseCase {
  override fun create(command: CreateMaterialCommand): Material = materialPersistencePort.save(Material.build(command))
    .getOrElse { throw GeometryUnknownException("error while saving material") }
  
  override fun getById(id: UUID): Material = materialPersistencePort.findById(id)
    .getOrElse { throw GeometryNotFoundException("material $id not found") }

  override fun update(command: UpdateMaterialCommand): Material {
    val mat = materialPersistencePort.findById(command.id)
      .getOrElse { throw GeometryNotFoundException("material ${command.id} not found") }
    return materialPersistencePort.save(mat.update(command))
      .getOrElse { throw GeometryUnknownException("error while saving material") }
  }

  override fun deleteById(id: UUID) = materialPersistencePort.deleteById(id)
    .getOrElse { throw GeometryUnknownException("error while deleting material") }

}
