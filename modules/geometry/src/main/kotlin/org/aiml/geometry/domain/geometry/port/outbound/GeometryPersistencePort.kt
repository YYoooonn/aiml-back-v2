package org.aiml.geometry.domain.geometry.port.outbound

import org.aiml.geometry.domain.geometry.model.Geometry
import java.util.*

interface GeometryPersistencePort {
  fun save(geometry: Geometry): Result<Geometry>
  fun findById(id: UUID): Result<Geometry>
  fun deleteById(id: UUID): Result<Unit>
}
