package org.aiml.object3d.base.application

import org.aiml.object3d.base.application.dto.Object3DDTO
import org.aiml.object3d.base.domain.port.inbound.Object3DQueryService
import org.aiml.object3d.base.domain.port.outbound.Object3DQueryPort
import org.springframework.stereotype.Service
import java.util.*

@Service
class Object3DQueryServiceImpl(
  private val object3DQueryPort: Object3DQueryPort
) : Object3DQueryService {
  override fun findById(id: UUID): Object3DDTO {
    TODO()
  }

  override fun findAllByIds(ids: List<UUID>): List<Object3DDTO> {
    TODO("Not yet implemented")
  }

  override fun findAllBySceneId(ids: List<UUID>): List<Object3DDTO> {
    TODO("Not yet implemented")
  }

  override fun findAllDescendantIds(rootId: UUID): List<UUID> {
    TODO("Not yet implemented")
  }


}
