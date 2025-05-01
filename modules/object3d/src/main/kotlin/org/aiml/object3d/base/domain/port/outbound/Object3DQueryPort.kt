package org.aiml.object3d.base.domain.port.outbound

import org.aiml.object3d.base.domain.model.Object3D
import java.util.*

interface Object3DQueryPort {
  fun findById(id: UUID): Result<Object3D>
  fun findAllByIds(ids: List<UUID>): Result<List<Object3D>>
  fun findAllBySceneId(sceneId: UUID): Result<List<Object3D>>
}
