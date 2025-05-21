package org.aiml.scene.application.facade

import org.aiml.object3d.base.application.facade.Object3DCommandFacade
import org.aiml.object3d.base.application.facade.Object3DQueryFacade
import org.aiml.scene.application.dto.SceneDTO
import org.aiml.scene.domain.port.inbound.SceneCommandService
import org.aiml.scene.domain.port.inbound.SceneQueryService
import org.springframework.stereotype.Service

@Service
class SceneAdminService(
  private val sceneCommandService: SceneCommandService,
  private val object3DCommandFacade: Object3DCommandFacade,
  private val sceneQueryService: SceneQueryService,
  private val object3DQueryFacade: Object3DQueryFacade
) {
  fun deleteAllScene() {
    object3DCommandFacade.deleteAll()
    return sceneCommandService.deleteAll()
  }

  fun loadAllScenes(): List<SceneDTO> {
    val scenes = sceneQueryService.findAll()
    return scenes.map { it.addChildren(object3DQueryFacade.getObjectTree(it.id)) }
  }
}
