package org.aiml.api.controller

import org.aiml.api.dto.auth.*
import org.aiml.api.dto.user.*
import org.aiml.api.common.response.*
import org.aiml.api.features.auth.service.AuthService
import org.aiml.user.application.UserServiceFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
  private val authService: AuthService,
  private val userServiceFacade: UserServiceFacade
) {

  @PostMapping("/register")
  fun register(@RequestBody request: UserRequest): ResponseEntity<ApiResponse<UserResponse>> {
    val user = userServiceFacade.register(request.toDTO())
    return created(UserResponse.from(user))
  }

  @PostMapping("/login")
  fun login(@RequestBody request: LoginRequest): ResponseEntity<ApiResponse<TokenResponse>> {
    val tokens = authService.login(request.username, request.password)
    return ok(TokenResponse.from(tokens))
  }

  @PostMapping("/reissue")
  fun reissue(@RequestBody request: ReissueRequest): ResponseEntity<ApiResponse<TokenResponse>> {
    val reissued = authService.reissue(request.refreshToken)
    return ok(TokenResponse.from(reissued))
  }
}
