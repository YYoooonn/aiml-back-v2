package org.aiml.api.controller

import org.aiml.api.common.response.ApiResponse
import org.aiml.api.common.response.ok
import org.aiml.api.dto.scene.SceneResponse
import org.aiml.scene.application.facade.SceneQueryFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/public")
class PublicController(
  private val sceneQueryFacade: SceneQueryFacade,
) {
  @GetMapping("/scene")
  fun getPublicProjectScenes(
    @RequestParam("pId") pId: UUID,
  ): ResponseEntity<ApiResponse<List<SceneResponse>>> {
    val scenes = sceneQueryFacade.loadPublicScene(pId)
    return ok(scenes.map { SceneResponse.fromDTO(it) })
  }
}
