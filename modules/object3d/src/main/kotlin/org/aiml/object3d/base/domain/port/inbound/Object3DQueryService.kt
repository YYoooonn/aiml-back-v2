package org.aiml.object3d.base.domain.port.inbound

import org.aiml.object3d.base.application.dto.Object3DDTO
import org.aiml.object3d.base.domain.model.Object3D
import java.util.*

// TODO NOT IN USE
interface Object3DQueryService {
  fun findById(id: UUID): Object3DDTO
  fun loadScene(sceneId: UUID): List<Object3DDTO>
}
