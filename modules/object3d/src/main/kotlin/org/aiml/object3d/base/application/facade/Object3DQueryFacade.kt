package org.aiml.object3d.base.application.facade

import org.aiml.object3d.base.application.dto.GroupDTO
import org.aiml.object3d.base.application.dto.MeshDTO
import org.aiml.object3d.base.application.dto.Object3DDTO
import org.aiml.object3d.base.domain.port.outbound.Object3DQueryPort
import org.aiml.object3d.group.domain.model.Group
import org.aiml.object3d.group.domain.port.inbound.GroupQueryService
import org.aiml.object3d.mesh.domain.model.Mesh
import org.aiml.object3d.mesh.domain.port.inbound.MeshQueryService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class Object3DQueryFacade(
  private val groupQueryService: GroupQueryService,
  private val meshQueryService: MeshQueryService,

  // FIXME to Service
  private val object3DQueryPort: Object3DQueryPort,
) {

  fun getObjectTree(sceneId: UUID): List<Object3DDTO> {
    val o = object3DQueryPort.findAllBySceneId(sceneId).getOrThrow()
    val objects = o.map {
      when (it) {
        is Mesh -> toMeshDTO(it)
        is Group -> toGroupDTO(it)
        else -> throw IllegalArgumentException("should be mesh or group for now")
      }
    }

    return Object3DTreeBuilder.build(objects)
  }

  fun getObjectById(id: UUID): Object3DDTO {
    return when (val found = object3DQueryPort.findById(id).getOrThrow()) {
      is Mesh -> toMeshDTO(found)
      is Group -> toGroupDTO(found)
      else -> TODO()
    }
  }

  fun getAllObjects(): List<Object3DDTO> {
    val objects = object3DQueryPort.findAll().getOrThrow()
    return objects.map {
      when (it) {
        is Mesh -> toMeshDTO(it)
        is Group -> toGroupDTO(it)
        else -> throw IllegalArgumentException("should be mesh or group for now")
      }
    }
  }

  private fun toMeshDTO(mesh: Mesh): Object3DDTO {
    val geometry = meshQueryService.getGeometry(mesh.geometryId)
    val material = meshQueryService.getMaterial(mesh.materialId)
    return MeshDTO.from(mesh, geometry, material)
  }

  private fun toGroupDTO(group: Group): Object3DDTO {
    val descendants = object3DQueryPort.findAllDescendantIds(group.id).getOrThrow()
    val objects = object3DQueryPort.findAllByIds(descendants).getOrThrow()
    val dtos = objects.map {
      when (it) {
        is Mesh -> toMeshDTO(it)
        is Group -> GroupDTO.from(it)
        else -> TODO("NOT IMPLEMENTED YET")
      }
    }
    val children = Object3DTreeBuilder.build(dtos, group.id)
    return GroupDTO.from(group, children)

  }

}
