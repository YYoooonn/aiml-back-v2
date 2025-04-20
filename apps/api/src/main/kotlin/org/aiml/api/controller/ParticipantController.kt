package org.aiml.api.controller

import org.aiml.api.dto.*
import org.aiml.api.mapper.ParticipantMapper
import org.aiml.project_user.domain.facade.ProjectUserFacade
import org.aiml.user.infra.security.CustomUserPrincipal
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/project/{projectId}/participants")
class ParticipantController(
  private val projectUserFacade: ProjectUserFacade,
  private val mapper: ParticipantMapper
) {

  @GetMapping
  fun getParticipants(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
    @PathVariable("projectId") projectId: UUID
  ): ResponseEntity<MultiProjectUserResponse> {
    val participants = projectUserFacade.getParticipants(principal.userId, projectId)
      .getOrThrow()
    return ResponseEntity.ok(mapper.toResponses(participants))
  }

  @PostMapping
  fun addParticipant(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
    @RequestBody request: ProjectParticipantRequest,
    @PathVariable("projectId") projectId: UUID
  ): ResponseEntity<ProjectUserResponse> {
    val participant = projectUserFacade.addParticipant(
      principal.userId,
      mapper.toCreateCommand(projectId, request)
    ).getOrThrow()
    return ResponseEntity.ok(mapper.toResponse(participant))
  }

  @PutMapping("/{username}")
  fun updateParticipant(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
    @RequestBody request: ProjectParticipantRequest,
    @PathVariable("projectId") projectId: UUID,
    @PathVariable("username") username: String
  ): ResponseEntity<ProjectUserResponse> {
    TODO()
  }

  @DeleteMapping("/{username}")
  fun deleteParticipant(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
    @PathVariable("projectId") projectId: UUID,
    @PathVariable("username") username: String
  ): ResponseEntity<Void> {
    projectUserFacade.deleteParticipant(
      principal.userId,
      mapper.toDeleteCommand(username, projectId)
    ).getOrThrow()
    return ResponseEntity.ok().build()
  }
}
