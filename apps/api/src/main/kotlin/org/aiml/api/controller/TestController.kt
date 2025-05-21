package org.aiml.api.controller

import org.aiml.api.common.response.*
import org.aiml.api.dto.object3d.Object3DRequest
import org.aiml.api.dto.object3d.Object3DResponse
import org.aiml.api.dto.object3d.toDTO
import org.aiml.api.dto.project.ProjectBaseResponse
import org.aiml.api.dto.scene.SceneResponse
import org.aiml.api.dto.user.UserResponse
import org.aiml.object3d.base.application.dto.MeshDTO
import org.aiml.object3d.base.application.facade.Object3DQueryFacade
import org.aiml.object3d.mesh.application.MeshCommandFacade
import org.aiml.object3d.mesh.domain.port.inbound.MeshCommandService
import org.aiml.project.application.ProjectAdminService
import org.aiml.project_user.application.ProjectUserAdminService
import org.aiml.project_user.application.dto.ProjectUserDTO
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
  private val meshCommandService: MeshCommandService,
  private val object3DQueryFacade: Object3DQueryFacade
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

  @GetMapping("/scenes")
  fun getAllScenes(): ResponseEntity<ApiResponse<List<SceneResponse>>> {
    val scenes = sceneAdminService.loadAllScenes()
    return ok(scenes.map { SceneResponse.fromDTO(it) })
  }

  @GetMapping("/projects")
  fun getAllProjects(): ResponseEntity<ApiResponse<List<ProjectBaseResponse>>> {
    val projects = projectAdminService.findAllProjects()
    return ok(projects.map { ProjectBaseResponse.from(it) })
  }

  @GetMapping("/projectUsers")
  fun getAllProjectUsers(): ResponseEntity<ApiResponse<List<ProjectUserDTO>>> {
    val pUsers = projectUserAdminService.findAllProjectUsers()
    return ok(pUsers)
  }

  @GetMapping("/object3d")
  fun getAllObject3D(): ResponseEntity<ApiResponse<List<Object3DResponse>>> {
    val all = object3DQueryFacade.getAllObjects()
    return ok(all.map { Object3DResponse.fromDTO(it) })
  }

  @DeleteMapping
  fun deleteAll(): ResponseEntity<ApiResponse<Nothing>> {
    sceneAdminService.deleteAllScene()
    userAdminService.deleteAllUsers()
    projectAdminService.deleteAllProjects()
    projectUserAdminService.deleteAllProjectUsers()
    return deleted()
  }

  @PostMapping("/mesh")
  internal fun createMesh(
    @RequestBody request: Object3DRequest,
  ): ResponseEntity<ApiResponse<Object3DResponse>> {
    val created = when (val mesh = request.toDTO()) {
      is MeshDTO -> meshCommandService.create(mesh, mesh.parentId)
      else -> throw IllegalArgumentException("test mesh only")
    }
    return ok(Object3DResponse.fromDTO(created))
  }


  // ------------------------ mesh testing ------------------------

//  @GetMapping("/mesh")
//  fun getAllMesh(): ResponseEntity<List<MeshDTO>> {
//    return ResponseEntity.ok(meshFacade)
//  }
//

//
//  @DeleteMapping("/mesh/{meshId}")
//  internal fun deleteMesh(
//    @PathVariable("meshId") meshId: UUID,
//  ): ResponseEntity<Void> {
//    meshFacade.delete(meshId).getOrThrow()
//    return ResponseEntity.ok().build()
//  }

}
