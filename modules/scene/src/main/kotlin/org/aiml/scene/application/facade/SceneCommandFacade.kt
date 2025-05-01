package org.aiml.scene.application.facade

import jakarta.transaction.Transactional
import org.aiml.object3d.base.application.facade.Object3DCommandFacade
import org.aiml.project_user.application.ProjectUserAuthService
import org.aiml.scene.application.dto.SceneDTO
import org.aiml.scene.domain.port.inbound.SceneCommandService
import org.aiml.scene.domain.port.inbound.SceneQueryService
import org.springframework.stereotype.Service
import java.util.*

@Transactional
@Service
class SceneCommandFacade(
  private val sceneCommandService: SceneCommandService,
  private val sceneQueryService: SceneQueryService,
  private val object3DCommandFacade: Object3DCommandFacade,
  private val authService: ProjectUserAuthService
) {
  fun initiate(userId: UUID, projectId: UUID): SceneDTO {
    authService.authenticateEditor(userId, projectId)
    val scene = sceneCommandService.initiate(projectId).getOrThrow()
    val children = object3DCommandFacade.initiate(scene.id)
    return scene.addChildren(children)
  }

  fun create(userId: UUID, scene: SceneDTO): SceneDTO {
    authenticate(userId, scene.projectId)
    val newScene = sceneCommandService.create(scene).getOrThrow()
    val children = object3DCommandFacade.initiate(scene.id)
    return newScene.addChildren(children)
  }

  fun delete(userId: UUID, sceneId: UUID) {
    authenticate(userId, sceneId)
    sceneCommandService.delete(sceneId).getOrThrow()
    object3DCommandFacade.deleteBySceneId(sceneId)
  }

  fun update(userId: UUID, scene: SceneDTO): SceneDTO {
    authenticate(userId, scene.id)
    val updated = sceneCommandService.update(scene).getOrThrow()
    val children = object3DCommandFacade.update(scene.children)
    return updated.addChildren(children)
  }

  // editor level authentication
  private fun authenticate(userId: UUID, sceneId: UUID) {
    val pId = sceneQueryService.findProjectId(sceneId)
    authService.authenticateEditor(userId, pId)
  }

}
