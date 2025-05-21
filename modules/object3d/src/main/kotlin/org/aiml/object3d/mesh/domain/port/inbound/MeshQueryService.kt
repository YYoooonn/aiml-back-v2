package org.aiml.object3d.mesh.domain.port.inbound

import org.aiml.object3d.base.application.dto.GeometryDTO
import org.aiml.object3d.base.application.dto.MaterialDTO
import org.aiml.object3d.base.application.dto.MeshDTO
import org.aiml.object3d.mesh.domain.model.geometry.Geometry
import java.util.UUID

interface MeshQueryService {
  fun findById(id: UUID): MeshDTO
  fun findBySceneId(sceneId: UUID): List<MeshDTO>

  fun getGeometry(id: UUID): GeometryDTO
  fun getMaterial(id: UUID): MaterialDTO
}
