package org.aiml.object3d.mesh.domain.port.outbound.command

import org.aiml.object3d.mesh.domain.model.geometry.Geometry
import java.util.*

interface GeometryCommandPort {
  fun save(geometry: Geometry): Geometry
  fun update(geometry: Geometry): Geometry
  fun deleteById(id: UUID)
  fun deleteByMeshId(id: UUID)

  fun deleteAllByMeshIds(ids: List<UUID>)

  fun deleteAll()
}
