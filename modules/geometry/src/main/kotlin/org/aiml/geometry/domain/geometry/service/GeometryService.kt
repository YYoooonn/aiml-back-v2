package org.aiml.geometry.domain.geometry.service

import org.aiml.geometry.domain.geometry.command.CreateGeometryCommand
import org.aiml.geometry.domain.geometry.command.UpdateGeometryCommand
import org.aiml.geometry.domain.geometry.model.Geometry
import org.aiml.geometry.domain.geometry.port.inbound.GeometryUseCase
import org.aiml.geometry.domain.geometry.port.outbound.GeometryPersistencePort
import java.util.*

class GeometryService(
  private val geometryPersistencePort: GeometryPersistencePort
) : GeometryUseCase {
  override fun create(command: CreateGeometryCommand): Geometry {
    TODO("Not yet implemented")
  }

  override fun getById(id: UUID): Geometry {
    TODO("Not yet implemented")
  }

  override fun update(command: UpdateGeometryCommand): Geometry {
    TODO("Not yet implemented")
  }

  override fun deleteById(id: UUID): Geometry {
    TODO("Not yet implemented")
  }
}
