package org.aiml.object3d.base.application.facade

import jakarta.transaction.Transactional
import org.aiml.object3d.base.application.dto.GroupDTO
import org.aiml.object3d.base.application.dto.MeshDTO
import org.aiml.object3d.base.application.dto.Object3DDTO
import org.aiml.object3d.base.domain.model.Object3DType
import org.aiml.object3d.base.domain.port.outbound.Object3DCommandPort
import org.aiml.object3d.base.domain.port.outbound.Object3DQueryPort
import org.aiml.object3d.group.domain.model.Group
import org.aiml.object3d.group.domain.port.inbound.GroupCommandService
import org.aiml.object3d.mesh.application.MeshCommandFacade
import org.aiml.object3d.mesh.domain.model.Mesh
import org.aiml.object3d.mesh.domain.port.inbound.MeshCommandService
import org.springframework.stereotype.Service
import java.util.*

@Transactional
@Service
class Object3DCommandFacade(
  private val meshCommandFacade: MeshCommandFacade,
  private val meshCommandService: MeshCommandService,
  private val groupCommandService: GroupCommandService,

  // FIXME to Service
  private val object3DCommandPort: Object3DCommandPort,
  private val object3DQueryPort: Object3DQueryPort
) {
  fun initiate(sceneId: UUID): List<Object3DDTO> {
    // TODO("initiate with camera, light, etc")
    return emptyList()
  }

  fun deleteBySceneId(sceneId: UUID) {
    // TODO mesh 는 geometry, material 을 직접 가지고 있지 않기 때문에 따로 삭제 해야함
    // 현재 느슨한 결합을 통해 구현. geometry, material 수정이 편리
    // 반면에 생성, 삭제 시 고려사항이 많음. 수정을 위한 서비스기에 수정 위주로 설정
    meshCommandFacade.deleteBySceneId(sceneId)
    object3DCommandPort.deleteBySceneId(sceneId)
  }

  fun deleteByIds(ids: List<UUID>) {
    object3DCommandPort.deleteByIds(ids)
  }

  fun update(children: List<Object3DDTO>): List<Object3DDTO> {
    TODO()
  }


  fun deleteAll() {
    meshCommandFacade.deleteAll()
    object3DCommandPort.deleteAll().getOrThrow()
  }

  fun createObject3d(dto: Object3DDTO): Object3DDTO {
    return when (dto) {
      is MeshDTO -> meshCommandService.create(dto, dto.parentId)
      is GroupDTO -> groupCommandService.create(dto)
      else -> throw IllegalArgumentException("Unsupported dto type ${dto.javaClass}")
    }
  }

  fun updateObject3d(dto: Object3DDTO): Object3DDTO {
    return when (dto) {
      is MeshDTO -> meshCommandService.update(dto, dto.parentId)
      is GroupDTO -> groupCommandService.update(dto)
      else -> throw IllegalArgumentException("Unsupported dto type ${dto.javaClass}")
    }
  }

  fun deleteObject3d(id: UUID) {
    val obj = object3DQueryPort.findById(id).getOrThrow()
    when (obj) {
      is Mesh -> meshCommandService.delete(id)
      is Group -> {
        val ids = object3DQueryPort.findAllDescendantIds(id).getOrThrow()
        object3DCommandPort.deleteByIds(ids)
      }

      else -> TODO("Not implemented yet")
    }
  }


}
