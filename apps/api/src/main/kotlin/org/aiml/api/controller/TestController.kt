package org.aiml.api.controller

import org.aiml.api.common.response.*
import org.aiml.api.dto.project.ProjectBaseResponse
import org.aiml.api.dto.user.UserResponse
import org.aiml.project.application.ProjectAdminService
import org.aiml.project_user.application.ProjectUserAdminService
import org.aiml.scene.application.facade.SceneAdminService
import org.aiml.user.application.UserAdminService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/test")
class TestController(
  private val userAdminService: UserAdminService,
  private val sceneAdminService: SceneAdminService,
  private val projectAdminService: ProjectAdminService,
  private val projectUserAdminService: ProjectUserAdminService,
) {
  @GetMapping
  fun test(): ResponseEntity<ApiResponse<String>> {
    return ok("Hello World")
  }

  // testing

  @GetMapping("/users")
  fun getAllUsers(): ResponseEntity<ApiResponse<List<UserResponse>>> {
    val users = userAdminService.getAllUsers()
    return ok(users.map { UserResponse.from(it) })
  }

  @GetMapping("/projects")
  fun getAllProjects(): ResponseEntity<ApiResponse<List<ProjectBaseResponse>>> {
    val projects = projectAdminService.findAllProjects()
    return ok(projects.map { ProjectBaseResponse.from(it) })
  }

  @DeleteMapping
  fun deleteAll(): ResponseEntity<ApiResponse<Nothing>> {
    sceneAdminService.deleteAllScene()
    userAdminService.deleteAllUsers()
    projectAdminService.deleteAllProjects()
    projectUserAdminService.deleteAllProjectUsers()
    return deleted()
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
