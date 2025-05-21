package org.aiml.api.controller

import org.aiml.api.common.response.ApiResponse
import org.aiml.api.common.response.ok
import org.aiml.api.dto.project.ProjectBaseResponse
import org.aiml.api.dto.project.ProjectPageResponse
import org.aiml.api.dto.scene.SceneResponse
import org.aiml.project.domain.port.inbound.ProjectQueryService
import org.aiml.scene.application.facade.SceneQueryFacade
import org.aiml.scene.domain.port.inbound.SceneQueryService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/archive")
class ArchiveController(
  private val projectQueryService: ProjectQueryService,
  private val sceneQueryFacade: SceneQueryFacade,
  private val sceneQueryService: SceneQueryService,
) {

  @GetMapping("/search/project")
  fun searchProjects(
    @RequestParam("query", defaultValue = "") query: String,
    @RequestParam("page", defaultValue = "0") page: Int,
    @RequestParam("size", defaultValue = "20") size: Int,
  ): ResponseEntity<ApiResponse<ProjectPageResponse>> {
    val pInfosPage = projectQueryService.searchByQuery(query, PageRequest.of(page, size)) // searchByQueryAndPageable
    return ok(ProjectPageResponse.from(pInfosPage))
  }

  /*
    projectId
    check if the project is archived: project.status == PUBLIC
    If the project is archived, return the scenes
  */
  @GetMapping("/{projectId}/scene")
  fun getArchivedScene(
    @PathVariable("projectId") projectId: UUID,
  ): ResponseEntity<ApiResponse<List<SceneResponse>>> {
    val scenes = sceneQueryFacade.loadPublicScene(projectId)
    return ok(scenes.map { SceneResponse.fromDTO(it) })
  }
}
