package org.aiml.api.controller

import org.aiml.api.dto.*
import org.aiml.api.mapper.ProjectMapper
import org.aiml.project_user.domain.facade.ProjectUserFacade
import org.aiml.user.infra.security.CustomUserPrincipal
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/project")
class ProjectController(
  private val projectUserFacade: ProjectUserFacade,
  private val mapper: ProjectMapper
) {

  @GetMapping
  fun getProjects(
    @AuthenticationPrincipal principal: CustomUserPrincipal
  ): ResponseEntity<MultiProjectResponse> {
    val pInfos = projectUserFacade.getProjectInfos(principal.userId)
      .getOrThrow()
    return ResponseEntity.ok(mapper.toMultiProjectResponse(pInfos))
  }

  @PostMapping
  fun createProject(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
    @RequestBody request: ProjectCreateRequest
  ): ResponseEntity<ProjectBaseResponse> {
    val pInfo = projectUserFacade.createProject(
      principal.userId,
      mapper.toCreateCommand(request)
    ).getOrThrow()
    return ResponseEntity.ok(mapper.toProjectResponse(pInfo))
  }

  @GetMapping("/{projectId}")
  fun getProject(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
    @PathVariable("projectId") projectId: UUID
  ): ResponseEntity<ProjectBaseResponse> {
    val pInfo = projectUserFacade.getProjectInfo(
      principal.userId, projectId
    ).getOrThrow()
    return ResponseEntity.ok(mapper.toProjectResponse(pInfo))
  }

  @PutMapping("/{projectId}")
  fun updateProject(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
    @PathVariable("projectId") projectId: UUID,
    @RequestBody request: ProjectUpdateRequest
  ): ResponseEntity<ProjectBaseResponse> {
    val pInfo = projectUserFacade.updateProject(
      principal.userId,
      mapper.toUpdateCommand(projectId, request)
    ).getOrThrow()
    return ResponseEntity.ok(mapper.toProjectResponse(pInfo))
  }

  @DeleteMapping("/{projectId}")
  fun deleteProject(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
    @PathVariable("projectId") projectId: UUID,
  ): ResponseEntity<Void> {
    projectUserFacade.deleteProject(
      principal.userId, projectId
    ).getOrThrow()
    return ResponseEntity.ok().build()
  }
}
