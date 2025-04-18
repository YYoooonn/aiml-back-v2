package org.aiml.api.auth.controller

import org.aiml.api.auth.dto.*
import org.aiml.api.auth.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
  private val authService: AuthService
) {

  @PostMapping("/signup")
  fun signup(@RequestBody request: SignupRequest): ResponseEntity<SignupResponse> {
    authService.signup(request.toCommand()).getOrThrow()
    return ResponseEntity.ok(SignupResponse(success = true, "sign up complete"))
  }

  @PostMapping("/login")
  fun login(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
    val tokens = authService.login(request)
    return ResponseEntity.ok(tokens)
  }

  @PostMapping("/reissue")
  fun reissue(@RequestBody request: ReissueRequest): ResponseEntity<ReissueResponse> {
    val accessToken = authService.reissue(request.refreshToken)
    return ResponseEntity.ok(ReissueResponse(accessToken))
  }
  
}
