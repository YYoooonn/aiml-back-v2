package org.aiml.api.controller

import org.aiml.api.common.response.*
import org.aiml.api.dto.project.ProjectBaseResponse
import org.aiml.api.dto.user.UserResponse
import org.aiml.project.domain.port.inbound.ProjectQueryService
import org.aiml.user.application.UserServiceFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/test")
class TestController(
  private val userServiceFacade: UserServiceFacade,
  private val projectQueryService: ProjectQueryService
) {
  @GetMapping
  fun test(): ResponseEntity<ApiResponse<String>> {
    return ok("Hello World")
  }

  // testing

  @GetMapping("/users")
  fun getAllUsers(): ResponseEntity<ApiResponse<List<UserResponse>>> {
    val users = userServiceFacade.getAllUsers()
    return ok(users.map { UserResponse.from(it) })
  }

  @GetMapping("/projects")
  fun getAllProjects(): ResponseEntity<ApiResponse<List<ProjectBaseResponse>>> {
    val projects = projectQueryService.findAll()
    return ok(projects.map { ProjectBaseResponse.from(it) })
  }


  // ------------------------ mesh testing ------------------------

//  @GetMapping("/mesh")
//  fun getAllMesh(): ResponseEntity<List<MeshDTO>> {
//    return ResponseEntity.ok(meshFacade)
//  }
//
//  @PatchMapping("/mesh/{meshId}")
//  internal fun updateMesh(
//    @RequestBody request: MeshUpdateDTO,
//    @PathVariable("meshId") meshId: UUID,
//  ): ResponseEntity<MeshDTO> {
//    return ResponseEntity.ok(meshFacade.update(request, meshId).getOrThrow())
//  }
//
//  @DeleteMapping("/mesh/{meshId}")
//  internal fun deleteMesh(
//    @PathVariable("meshId") meshId: UUID,
//  ): ResponseEntity<Void> {
//    meshFacade.delete(meshId).getOrThrow()
//    return ResponseEntity.ok().build()
//  }

}
