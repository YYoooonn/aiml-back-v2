package org.aiml.api.controller

import org.aiml.api.common.response.*
import org.aiml.api.dto.project.ProjectParticipantRequest
import org.aiml.api.dto.project.ProjectUserResponse
import org.aiml.project_user.application.facade.ProjectUserFacade
import org.aiml.user.infra.security.CustomUserPrincipal
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/project/{projectId}/participants")
class ParticipantController(
  private val projectUserFacade: ProjectUserFacade,
) {

  @GetMapping
  fun getParticipants(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
    @PathVariable("projectId") projectId: UUID
  ): ResponseEntity<ApiResponse<List<ProjectUserResponse>>> {
    val participants = projectUserFacade.findUsers(principal.userId, projectId)
    return ok(participants.map { ProjectUserResponse.from(it) })
  }

  @PostMapping
  fun addParticipant(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
    @RequestBody request: ProjectParticipantRequest,
    @PathVariable("projectId") projectId: UUID
  ): ResponseEntity<ApiResponse<ProjectUserResponse>> {
    val participant = projectUserFacade.addParticipant(
      principal.userId,
      projectId,
      request.username,
      request.role
    )
    return created(ProjectUserResponse.from(participant))
  }

  @PutMapping("/{username}")
  fun updateParticipant(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
    @RequestBody request: ProjectParticipantRequest,
    @PathVariable("projectId") projectId: UUID,
    @PathVariable("username") username: String
  ): ResponseEntity<ApiResponse<ProjectUserResponse>> {
    val participant = projectUserFacade.updateParticipant(
      principal.userId,
      projectId,
      username,
      request.role
    )
    return ok(ProjectUserResponse.from(participant))
  }

  @DeleteMapping("/{username}")
  fun deleteParticipant(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
    @PathVariable("projectId") projectId: UUID,
    @PathVariable("username") username: String
  ): ResponseEntity<ApiResponse<Nothing>> {
    projectUserFacade.removeParticipant(principal.userId, projectId, username)
    return deleted()
  }
}
