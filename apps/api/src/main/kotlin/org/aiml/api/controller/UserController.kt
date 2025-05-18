package org.aiml.api.controller

import org.aiml.api.dto.user.*
import org.aiml.api.common.response.*
import org.aiml.project_user.application.facade.UserProjectCommandFacade
import org.aiml.scene.application.facade.SceneCommandFacade
import org.aiml.user.infra.security.CustomUserPrincipal
import org.aiml.user.application.UserServiceFacade
import org.aiml.user.domain.port.inbound.UserCoreCommandService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(
  private val userServiceFacade: UserServiceFacade,
  private val userCoreCommandService: UserCoreCommandService,
  private val userProjectCommandFacade: UserProjectCommandFacade,
  private val sceneCommandFacade: SceneCommandFacade
) {
  @GetMapping("/me")
  fun getUser(
    @AuthenticationPrincipal principal: CustomUserPrincipal
  ): ResponseEntity<ApiResponse<UserResponse>> {
    val user = userServiceFacade.getUserInfo(principal.userId)
    return ok(UserResponse.from(user))
  }

  @PutMapping("/me")
  fun updateUser(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
    @RequestBody request: UserCoreRequest
  ): ResponseEntity<ApiResponse<UserCoreResponse>> {
    // 여기서 core update, revalidate token 필요
    val user = userCoreCommandService.update(request.toDTO(principal.userId))
    return ok(data = UserCoreResponse.from(user), revalidate = true)
  }

  @DeleteMapping("/me")
  fun deleteUser(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
  ): ResponseEntity<ApiResponse<Void>> {
    userProjectCommandFacade.deleteByUserId(principal.userId)
    sceneCommandFacade.deleteByUserId(principal.userId)
    return deleted()
  }
}
