package org.aiml.scene.application.facade

import org.aiml.object3d.base.application.dto.Object3DDTO
import org.aiml.object3d.base.application.facade.Object3DQueryFacade
import org.aiml.project_user.application.ProjectUserAuthService
import org.aiml.scene.application.dto.SceneDTO
import org.aiml.scene.domain.port.inbound.SceneQueryService
import org.springframework.stereotype.Service
import java.util.*

@Service
class SceneQueryFacade(
  private val sceneQueryService: SceneQueryService,
  private val object3DQueryFacade: Object3DQueryFacade,
  private val authService: ProjectUserAuthService
) {
  fun loadScene(userId: UUID, sceneId: UUID): SceneDTO {
    val scene = sceneQueryService.findById(sceneId)
    authService.authenticateViewer(userId, scene.projectId)

    val children = loadObjectTree(scene.id)
    return scene.addChildren(children)
  }

  fun loadScenesByProject(userId: UUID, projectId: UUID): List<SceneDTO> {
    authService.authenticateViewer(userId, projectId)
    val scenes = sceneQueryService.findByProjectId(projectId)
    if (scenes.isEmpty()) return emptyList()
    return scenes.map { it.addChildren(loadObjectTree(it.id)) }
  }

  private fun loadObjectTree(sceneId: UUID): List<Object3DDTO> {
    return object3DQueryFacade.getObjectTree(sceneId)
  }

}
