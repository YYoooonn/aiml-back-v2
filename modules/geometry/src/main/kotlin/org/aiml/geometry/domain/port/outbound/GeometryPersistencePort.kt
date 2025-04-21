package org.aiml.geometry.domain.port.outbound

import org.aiml.geometry.domain.model.Geometry
import java.util.*

interface GeometryPersistencePort {
  fun save(geometry: org.aiml.geometry.domain.model.Geometry): Result<org.aiml.geometry.domain.model.Geometry>
  fun findById(id: UUID): Result<org.aiml.geometry.domain.model.Geometry>
  fun deleteById(id: UUID): Result<org.aiml.geometry.domain.model.Geometry>
}
