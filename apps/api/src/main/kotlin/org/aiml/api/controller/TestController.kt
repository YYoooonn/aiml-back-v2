package org.aiml.api.controller

import org.aiml.api.dto.ProjectCreateRequest
import org.aiml.geometry.application.MeshFacade
import org.aiml.geometry.application.dto.MeshDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/test")
class TestController(
  private val meshFacade: MeshFacade
) {
  @GetMapping
  fun test(): ResponseEntity<String> {
    return ResponseEntity.ok("Hello World")
  }

  @PostMapping("/mesh")
  fun testMesh(
    @RequestBody request: MeshDTO
  ): ResponseEntity<MeshDTO> {
    return ResponseEntity.ok(meshFacade.save(request).getOrThrow())
  }
}
