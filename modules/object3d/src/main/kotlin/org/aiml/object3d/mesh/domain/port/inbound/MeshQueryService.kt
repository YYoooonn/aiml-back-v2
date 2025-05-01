package org.aiml.object3d.mesh.domain.port.inbound

import org.aiml.object3d.base.application.dto.MeshDTO
import java.util.UUID

interface MeshQueryService {
  fun findById(id: UUID): MeshDTO
  fun findBySceneId(sceneId: UUID): List<MeshDTO>
}
