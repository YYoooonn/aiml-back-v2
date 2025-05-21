package org.aiml.api.controller

import org.aiml.api.common.response.ApiResponse
import org.aiml.api.common.response.deleted
import org.aiml.api.common.response.ok
import org.aiml.api.dto.object3d.Object3DRequest
import org.aiml.api.dto.object3d.Object3DResponse
import org.aiml.api.dto.object3d.toDTO
import org.aiml.object3d.base.application.facade.Object3DCommandFacade
import org.aiml.object3d.base.application.facade.Object3DQueryFacade
import org.aiml.object3d.base.domain.model.Object3D
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/object3d")
class Object3DController(
  private val object3DCommandFacade: Object3DCommandFacade,
  private val object3DQueryFacade: Object3DQueryFacade
) {

  @GetMapping("/{objectId}")
  fun getObject3D(
    @PathVariable objectId: UUID
  ): ResponseEntity<ApiResponse<Object3DResponse>> {
    val dto = object3DQueryFacade.getObjectById(objectId)
    return ok(Object3DResponse.fromDTO(dto))
  }

  @PostMapping
  fun createObject3D(
    @RequestBody request: Object3DRequest
  ): ResponseEntity<ApiResponse<Object3DResponse>> {
    val dto = object3DCommandFacade.createObject3d(request.toDTO())
    return ok(Object3DResponse.fromDTO(dto))
  }

  @DeleteMapping("/{objectId}")
  fun deleteObject3D(
    @PathVariable objectId: UUID
  ): ResponseEntity<ApiResponse<Nothing>> {
    object3DCommandFacade.deleteObject3d(objectId)
    return deleted()
  }

  @PutMapping("/{objectId}")
  fun updateObject3D(
    @PathVariable objectId: UUID,
    @RequestBody request: Object3DRequest
  ): ResponseEntity<ApiResponse<Object3DResponse>> {
    val dto = object3DCommandFacade.updateObject3d(request.toDTO())
    return ok(Object3DResponse.fromDTO(dto))
  }
}
