package org.aiml.geometry.domain.port.inbound

import org.aiml.geometry.domain.command.CreateGeometryCommand
import org.aiml.geometry.domain.model.Geometry
import java.util.UUID

interface GeometryUseCase {
  fun createGeometry(command: org.aiml.geometry.domain.command.CreateGeometryCommand): org.aiml.geometry.domain.model.Geometry
  fun getGeometryById(geometryId: UUID): org.aiml.geometry.domain.model.Geometry
}
