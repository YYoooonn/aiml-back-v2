package org.aiml.api.dto.object3d

import org.aiml.object3d.base.application.dto.*
import java.util.*

data class Object3DRequest(
  val sceneId: UUID? = null,
  val id: UUID?,
  val name: String?,
  // TODO to enum, kotlin 에서 json이더라도 동일 문자열 가지는 경우 자동 매핑
  val type: String,
  val transform: TransformDTO,
  val visible: Boolean = true,
  val parentId: UUID? = null,

  val geometry: GeometryDTO? = null,
  val material: MaterialDTO? = null,
  val children: List<Object3DRequest> = emptyList(),
)

// for solely used object3DRequest - object3D Controller
fun Object3DRequest.toDTO(): Object3DDTO {
  if (this.sceneId == null) throw IllegalArgumentException("SceneId cannot be null")
  return this.toDTO(this.sceneId, this.parentId)
}

/* for SceneRequest or for group children */
fun Object3DRequest.toDTO(sceneId: UUID, parentId: UUID? = null): Object3DDTO {
  return when (this.type) {
    "MESH" -> this.toMeshDTO(sceneId, parentId)
    "GROUP" -> this.toGroupDTO(sceneId, parentId)
    else -> throw IllegalArgumentException("Unsupported type ${this.type}")
  }
}

fun Object3DRequest.toMeshDTO(sceneId: UUID, parentId: UUID? = null): MeshDTO {
  if (geometry == null || material == null) throw RuntimeException("invalid mesh request")
  return MeshDTO(
    id = this.id,
    sceneId = sceneId,
    parentId = parentId,
    name = this.name ?: "untitled mesh",
    type = this.type,
    transform = this.transform,
    visible = this.visible,
    geometry = this.geometry,
    material = this.material,
  )
}

fun Object3DRequest.toGroupDTO(sceneId: UUID, parentId: UUID? = null): GroupDTO {
  return GroupDTO(
    id = this.id,
    parentId = parentId,
    sceneId = sceneId,
    name = this.name ?: "untitled group",
    type = this.type,
    transform = this.transform,
    visible = this.visible,
    children = this.children.map { it.toDTO(sceneId, this.id) },
  )
}


