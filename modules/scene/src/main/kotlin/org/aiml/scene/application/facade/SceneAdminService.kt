package org.aiml.scene.application.facade

import org.aiml.object3d.base.application.facade.Object3DCommandFacade
import org.aiml.scene.domain.port.inbound.SceneCommandService
import org.springframework.stereotype.Service

@Service
class SceneAdminService(
  private val sceneCommandService: SceneCommandService,
  private val object3DCommandFacade: Object3DCommandFacade
) {
  fun deleteAllScene() {
    object3DCommandFacade.deleteAll()
    return sceneCommandService.deleteAll()
  }
}
