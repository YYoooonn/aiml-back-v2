package org.aiml.object3d.base.infra.adapter

import org.aiml.object3d.base.domain.port.outbound.Object3DCommandPort
import org.aiml.object3d.base.infra.persistence.repository.Object3DRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class Object3DCommandAdapter(
  private val object3DRepository: Object3DRepository,
) : Object3DCommandPort {
  override fun deleteBySceneId(sceneId: UUID): Result<Unit> = runCatching {
    object3DRepository.deleteAllBySceneId(sceneId)
  }

  override fun deleteAll(): Result<Unit> = runCatching {
    object3DRepository.deleteAll()
  }

  override fun deleteByIds(ids: List<UUID>): Result<Unit> = runCatching {
    object3DRepository.deleteAllById(ids)
  }
}
