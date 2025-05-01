package org.aiml.object3d.mesh.domain.port.outbound.query

import org.aiml.object3d.mesh.domain.model.Mesh
import java.util.UUID

interface MeshQueryPort {
  fun findById(id: UUID): Result<Mesh>
  fun findBySceneId(sceneId: UUID): Result<List<Mesh>>
}
