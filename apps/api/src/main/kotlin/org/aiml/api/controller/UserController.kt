package org.aiml.api.controller

import org.aiml.api.dto.user.*
import org.aiml.api.common.response.*
import org.aiml.libs.common.file.FileStorage
import org.aiml.project_user.application.facade.UserProjectCommandFacade
import org.aiml.scene.application.facade.SceneCommandFacade
import org.aiml.user.infra.security.CustomUserPrincipal
import org.aiml.user.application.UserServiceFacade
import org.aiml.user.domain.port.inbound.UserCoreCommandService
import org.aiml.user.domain.port.inbound.UserProfileCommandService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/user")
class UserController(
  private val userServiceFacade: UserServiceFacade,
  private val userCoreCommandService: UserCoreCommandService,
  private val userProfileCommandService: UserProfileCommandService,
  private val userProjectCommandFacade: UserProjectCommandFacade,
  private val sceneCommandFacade: SceneCommandFacade,
  private val fileStorage: FileStorage
) {
  @GetMapping("/me")
  fun getUser(
    @AuthenticationPrincipal principal: CustomUserPrincipal
  ): ResponseEntity<ApiResponse<UserResponse>> {
    val user = userServiceFacade.getUserInfo(principal.userId)
    return ok(UserResponse.from(user, fileStorage))
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

  @PutMapping("/me/profile") // content-type: multipart/form-data
  fun updateUserProfile(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
    @RequestPart("data") request: UserProfileRequest, // content-type: application/json
    @RequestPart("file", required = false) file: MultipartFile?, // Not required. All types of files are allowed.
  ): ResponseEntity<ApiResponse<UserProfileResponse>> {
    val user = userProfileCommandService.update(request.toDTO(principal.userId), file)
    return ok(UserProfileResponse.from(user, fileStorage))
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
