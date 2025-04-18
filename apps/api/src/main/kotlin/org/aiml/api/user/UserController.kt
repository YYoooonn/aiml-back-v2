package org.aiml.api.user

import org.aiml.api.user.dto.*
import org.aiml.user.domain.exception.UserNotFoundException
import org.aiml.user.domain.port.input.UserUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/users")
class UserController(
  private val userUseCase: UserUseCase
) {

//  @PostMapping
//  fun createUser(@RequestBody request: UserRequest): ResponseEntity<UserResponse> {
//    val createdUser = userUseCase.createUser(request.toCommand())
//    return ResponseEntity.ok(createdUser.toResponse())
//  }


  @GetMapping
  fun getUsers(): ResponseEntity<List<UserResponse>> {
    val users = userUseCase.getAllUsers()
    return ResponseEntity.ok(users.map { it.toResponse() })
  }

  @GetMapping("/{username}")
  fun getUser(@PathVariable username: String): ResponseEntity<UserResponse> {
    val user = userUseCase.getUserByUsername(username).getOrThrow()
    return ResponseEntity.ok(user.toResponse())
  }

}
