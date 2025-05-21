package org.aiml.object3d.base.domain.port.inbound

import org.aiml.object3d.base.application.dto.Object3DDTO
import org.aiml.object3d.base.domain.model.Object3D
import java.util.*

interface Object3DQueryService {
  fun findById(id: UUID): Object3DDTO
  fun findAllByIds(ids: List<UUID>): List<Object3DDTO>
  fun findAllBySceneId(ids: List<UUID>): List<Object3DDTO>

  fun findAllDescendantIds(rootId: UUID): List<UUID>
}
