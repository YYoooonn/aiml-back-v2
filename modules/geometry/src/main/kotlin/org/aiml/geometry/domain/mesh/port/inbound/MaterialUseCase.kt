package org.aiml.geometry.domain.mesh.port.inbound

import org.aiml.geometry.domain.mesh.command.CreateMaterialCommand
import org.aiml.geometry.domain.mesh.command.UpdateMaterialCommand
import org.aiml.geometry.domain.mesh.model.Material
import java.util.*

interface MaterialUseCase {
  fun create(command: CreateMaterialCommand): Material
  fun update(command: UpdateMaterialCommand): Material
  fun getById(id: UUID): Material
  fun deleteById(id: UUID)
}
