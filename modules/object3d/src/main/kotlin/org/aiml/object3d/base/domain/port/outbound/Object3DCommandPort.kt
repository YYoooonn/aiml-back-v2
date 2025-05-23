package org.aiml.object3d.base.domain.port.outbound

import java.util.*

interface Object3DCommandPort {
  fun deleteBySceneId(sceneId: UUID): Result<Unit>
  fun deleteAll(): Result<Unit>
  fun deleteByIds(ids: List<UUID>): Result<Unit>
}
