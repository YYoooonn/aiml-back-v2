package org.aiml.object3d.mesh.application

import org.aiml.object3d.base.application.dto.MeshDTO
import org.aiml.object3d.mesh.domain.port.inbound.MeshCommandService
import org.aiml.object3d.mesh.domain.port.outbound.query.MeshQueryPort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MeshCommandFacade(
  // query service 를 사용하지 않고, id 조회만을 위한 port 사용
  private val meshQueryPort: MeshQueryPort,
  private val meshCommandService: MeshCommandService
) {
  fun deleteBySceneId(sceneId: UUID) {
    val meshIds = meshQueryPort.findBySceneId(sceneId).getOrThrow().map { it.id }.distinct()
    return meshCommandService.deleteAllByIds(meshIds)
  }

  fun deleteAll() {
    return meshCommandService.deleteAll()
  }

}
