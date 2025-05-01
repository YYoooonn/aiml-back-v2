package org.aiml.object3d.group.domain.port.outbound

import org.aiml.object3d.group.domain.model.Group
import java.util.UUID

interface GroupQueryPort {
  fun findBySceneId(sceneId: UUID): Result<List<Group>>
  fun findById(id: UUID): Result<Group>
}
