package org.aiml.api.controller

import org.aiml.api.common.response.*
import org.aiml.api.dto.scene.*
import org.aiml.scene.application.facade.SceneCommandFacade
import org.aiml.scene.application.facade.SceneQueryFacade
import org.aiml.user.infra.security.CustomUserPrincipal
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/api/scene")
class SceneController(
  private val sceneQueryFacade: SceneQueryFacade,
  private val sceneCommandFacade: SceneCommandFacade,
) {

  @GetMapping("/{sceneId}")
  fun getScene(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
    @PathVariable sceneId: UUID
  ): ResponseEntity<ApiResponse<SceneResponse>> {
    val scene = sceneQueryFacade.loadScene(principal.userId, sceneId)
    return ok(SceneResponse.fromDTO(scene))
  }

  @DeleteMapping("/{sceneId}")
  fun deleteScene(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
    @PathVariable sceneId: UUID
  ): ResponseEntity<ApiResponse<Nothing>> {
    sceneCommandFacade.delete(principal.userId, sceneId)
    return deleted()
  }

  @PutMapping("/{sceneId}")
  fun updateScene(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
    @PathVariable sceneId: UUID,
    @RequestBody request: SceneRequest
  ): ResponseEntity<ApiResponse<SceneResponse>> {
    val scene = sceneCommandFacade.update(principal.userId, request.toDTO())
    return created(SceneResponse.fromDTO(scene))
  }

}
