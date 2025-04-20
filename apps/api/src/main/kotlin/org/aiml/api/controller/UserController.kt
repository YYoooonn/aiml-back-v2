package org.aiml.api.controller

import org.aiml.api.dto.*
import org.aiml.api.mapper.UserMapper
import org.aiml.user.infra.security.CustomUserPrincipal
import org.aiml.user.application.UserServiceFacade
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(
  private val userServiceFacade: UserServiceFacade,
  private val userMapper: UserMapper
) {

  @PostMapping("/register")
  fun register(@RequestBody request: UserCreateRequest): ResponseEntity<UserProfileResponse> {
    // TODO 여기서 or auth 에서, 일단은 현재 user 는 token 없이 진입 못함
    val user = userServiceFacade.register(request.toCommand()).getOrThrow()
    return ResponseEntity.ok(userMapper.toUserProfileResponse(user))
  }

  @GetMapping("/me")
  fun getUser(
    @AuthenticationPrincipal principal: CustomUserPrincipal
  ): ResponseEntity<UserBaseResponse> {
    val user = userServiceFacade.getUser(principal).getOrThrow()
    return ResponseEntity.ok(userMapper.toUserResponse(user))
  }

  @PutMapping("/me")
  fun updateUser(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
    @RequestBody request: UserUpdateRequest
  ): ResponseEntity<RevalidateResponse> {
    // 여기서 core update, revalidate token 필요
    val user = userServiceFacade.updateUser(principal, request.toCommand()).getOrThrow()
    return ResponseEntity.ok(userMapper.toRevalidateResponse(user))
  }

  @DeleteMapping("/me")
  fun deleteUser(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
  ): ResponseEntity<Void> {
    userServiceFacade.delete(principal).getOrThrow()
    return ResponseEntity.ok().build()
  }

  @GetMapping("/me/profile")
  fun getUserProfile(
    @AuthenticationPrincipal principal: CustomUserPrincipal
  ): ResponseEntity<UserProfileResponse> {
    val profile = userServiceFacade.getProfile(principal).getOrThrow()
    return ResponseEntity.ok(userMapper.toUserProfileResponse(profile))
  }

  @PutMapping("/me/profile")
  fun updateUserProfile(
    @AuthenticationPrincipal principal: CustomUserPrincipal,
    @RequestBody request: UserUpdateRequest
  ): ResponseEntity<UpdateResponse> {
    // 여기서 profile 만 update, revalidate token 필요 없음
    val profile = userServiceFacade.updateUserProfile(principal, request.toCommand()).getOrThrow()
    return ResponseEntity.ok(userMapper.toUpdateResponse(profile))
  }

}
