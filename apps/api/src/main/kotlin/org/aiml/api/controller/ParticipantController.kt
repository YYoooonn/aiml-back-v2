package org.aiml.api.controller

import org.aiml.api.common.response.*
import org.aiml.api.dto.project.ProjectParticipantRequest
import org.aiml.api.dto.project.ProjectUserResponse
import org.aiml.project_user.application.facade.ParticipantService
import org.aiml.user.infra.security.CustomUserPrincipal
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/{projectId}/participant")
class ParticipantController(
  private val participantService: ParticipantService,
) {

  @GetMapping
  fun getParticipants(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
    @PathVariable("projectId") projectId: UUID
  ): ResponseEntity<ApiResponse<List<ProjectUserResponse>>> {
    val participants = participantService.findParticipants(principal.userId, projectId)
    return ok(participants.map { ProjectUserResponse.from(it) })
  }

  @PostMapping
  fun addParticipant(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
    @RequestBody request: ProjectParticipantRequest,
    @PathVariable("projectId") projectId: UUID
  ): ResponseEntity<ApiResponse<ProjectUserResponse>> {
    val participant = participantService.addParticipant(
      principal.userId,
      request.toDTO(projectId)
    )
    return created(ProjectUserResponse.from(participant))
  }

  @PutMapping
  fun updateParticipant(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
    @RequestBody request: ProjectParticipantRequest,
    @PathVariable("projectId") projectId: UUID
  ): ResponseEntity<ApiResponse<ProjectUserResponse>> {
    val participant = participantService.updateParticipant(
      principal.userId,
      request.toDTO(projectId)
    )
    return ok(ProjectUserResponse.from(participant))
  }

  @DeleteMapping("/{username}")
  fun deleteParticipant(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
    @PathVariable("projectId") projectId: UUID,
    @PathVariable("username") username: String
  ): ResponseEntity<ApiResponse<Nothing>> {
    participantService.removeParticipant(principal.userId, projectId, username)
    return deleted()
  }
}
