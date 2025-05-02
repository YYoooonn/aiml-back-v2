package org.aiml.object3d.group.domain.port.inbound

import org.aiml.object3d.base.application.dto.GroupDTO
import java.util.UUID

interface GroupQueryService {
  fun findBySceneId(sceneId: UUID): List<GroupDTO>
  fun findById(id: UUID): GroupDTO
}
