package org.aiml.api.controller

import org.aiml.api.common.response.ApiResponse
import org.aiml.api.dto.object3d.Object3DRequest
import org.aiml.api.dto.object3d.Object3DResponse
import org.aiml.object3d.base.domain.model.Object3D
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/object3d")
class Object3DController {

  @GetMapping("/{objId}")
  fun getObject3D(
    @PathVariable objId: String
  ): ResponseEntity<ApiResponse<Object3DResponse>> {
    TODO()
  }

  @PostMapping
  fun createObject3D(
    @RequestBody object3D: Object3DRequest
  ): ResponseEntity<ApiResponse<Object3DResponse>> {
    TODO()
  }

  @DeleteMapping("/{objId}")
  fun deleteObject3D(
    @PathVariable objId: String
  ): ResponseEntity<ApiResponse<Nothing>> {
    TODO()
  }

  @PutMapping("/{objId}")
  fun updateObject3D(
    @PathVariable objId: String,
    @RequestBody object3D: Object3D
  ): ResponseEntity<ApiResponse<Object3DResponse>> {
    TODO()
  }
}
