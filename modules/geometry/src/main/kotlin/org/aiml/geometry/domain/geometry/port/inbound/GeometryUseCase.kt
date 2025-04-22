package org.aiml.geometry.domain.geometry.port.inbound

import org.aiml.geometry.domain.geometry.command.CreateGeometryCommand
import org.aiml.geometry.domain.geometry.command.UpdateGeometryCommand
import org.aiml.geometry.domain.geometry.model.Geometry
import java.util.UUID

interface GeometryUseCase {
  fun create(command: CreateGeometryCommand): Geometry
  fun getById(id: UUID): Geometry
  fun update(command: UpdateGeometryCommand): Geometry
  fun deleteById(id: UUID): Geometry
}
