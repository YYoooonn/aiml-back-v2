package org.aiml.scene.application.facade

import jakarta.transaction.Transactional
import org.aiml.object3d.base.application.facade.Object3DCommandFacade
import org.aiml.project_user.application.ProjectUserAuthService
import org.aiml.project_user.domain.port.inbound.ProjectUserQueryService
import org.aiml.scene.application.dto.SceneDTO
import org.aiml.scene.domain.port.inbound.SceneCommandService
import org.aiml.scene.domain.port.inbound.SceneQueryService
import org.springframework.stereotype.Service
import java.util.*

@Service
class SceneCommandFacade(
  private val sceneCommandService: SceneCommandService,
  private val sceneQueryService: SceneQueryService,
  private val object3DCommandFacade: Object3DCommandFacade,
  private val projectUserQueryService: ProjectUserQueryService,
  private val authService: ProjectUserAuthService
) {
  @Transactional
  fun initiate(userId: UUID, projectId: UUID): SceneDTO {
    authService.authenticateEditor(userId, projectId)
    val scene = sceneCommandService.initiate(projectId).getOrThrow()
    val children = object3DCommandFacade.initiate(scene.id)
    return scene.addChildren(children)
  }

  @Transactional
  fun create(userId: UUID, scene: SceneDTO): SceneDTO {
    authenticate(userId, scene.projectId)
    val newScene = sceneCommandService.create(scene).getOrThrow()
    val children = object3DCommandFacade.initiate(scene.id)
    return newScene.addChildren(children)
  }

  @Transactional
  fun delete(userId: UUID, sceneId: UUID) {
    authenticate(userId, sceneId)
    sceneCommandService.delete(sceneId).getOrThrow()
    object3DCommandFacade.deleteBySceneId(sceneId)
  }

  @Transactional
  fun update(userId: UUID, scene: SceneDTO): SceneDTO {
    authenticate(userId, scene.id)
    val updated = sceneCommandService.update(scene).getOrThrow()
    val children = object3DCommandFacade.update(scene.children)
    return updated.addChildren(children)
  }


  // ------------- project-user

  fun deleteByUserId(userId: UUID) {
    val pIds = projectUserQueryService.findUserOwnedProjects(userId)
      .map { it.projectId }
    sceneQueryService.findByProjectIds(pIds).map { scene ->
      object3DCommandFacade.deleteBySceneId(scene.id)
      sceneCommandService.delete(scene.id).getOrThrow()
    }
  }


  // editor level authentication
  private fun authenticate(userId: UUID, sceneId: UUID) {
    val pId = sceneQueryService.findProjectId(sceneId)
    authService.authenticateEditor(userId, pId)
  }

}
