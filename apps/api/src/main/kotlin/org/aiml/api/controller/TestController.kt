package org.aiml.api.controller

import org.aiml.api.dto.ProjectCreateRequest
import org.aiml.geometry.application.MeshFacade
import org.aiml.geometry.application.dto.MeshDTO
import org.aiml.geometry.application.dto.MeshUpdateDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/test")
class TestController(
  private val meshFacade: MeshFacade
) {
  @GetMapping
  fun test(): ResponseEntity<String> {
    return ResponseEntity.ok("Hello World")
  }


  // ------------------------ mesh testing ------------------------

  @GetMapping("/mesh")
  fun getAllMesh(): ResponseEntity<List<MeshDTO>> {
    return ResponseEntity.ok(meshFacade.getAll().getOrThrow())
  }

  @PostMapping("/mesh")
  fun postMesh(
    @RequestBody request: MeshDTO
  ): ResponseEntity<MeshDTO> {
    return ResponseEntity.ok(meshFacade.save(request).getOrThrow())
  }

  @PatchMapping("/mesh/{meshId}")
  internal fun updateMesh(
    @RequestBody request: MeshUpdateDTO,
    @PathVariable("meshId") meshId: UUID,
  ): ResponseEntity<MeshDTO> {
    return ResponseEntity.ok(meshFacade.update(request, meshId).getOrThrow())
  }

  @DeleteMapping("/mesh/{meshId}")
  internal fun deleteMesh(
    @PathVariable("meshId") meshId: UUID,
  ): ResponseEntity<Void> {
    meshFacade.delete(meshId).getOrThrow()
    return ResponseEntity.ok().build()
  }


}
