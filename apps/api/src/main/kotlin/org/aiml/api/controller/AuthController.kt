package org.aiml.api.controller

import org.aiml.api.security.AuthService
import org.aiml.api.dto.LoginRequest
import org.aiml.api.dto.LoginResponse
import org.aiml.api.dto.ReissueRequest
import org.aiml.api.dto.ReissueResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
  private val authService: AuthService
) {

  @PostMapping("/login")
  fun login(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
    val tokens = authService.login(request)
    return ResponseEntity.ok(tokens)
  }

  @PostMapping("/reissue")
  fun reissue(@RequestBody request: ReissueRequest): ResponseEntity<ReissueResponse> {
    return ResponseEntity.ok(authService.reissue(request))
  }

}
