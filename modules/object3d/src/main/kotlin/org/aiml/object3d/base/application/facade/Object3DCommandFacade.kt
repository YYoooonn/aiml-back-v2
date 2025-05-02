package org.aiml.object3d.base.application.facade

import jakarta.transaction.Transactional
import org.aiml.object3d.base.application.dto.Object3DDTO
import org.aiml.object3d.base.domain.port.outbound.Object3DCommandPort
import org.aiml.object3d.group.domain.port.inbound.GroupCommandService
import org.aiml.object3d.mesh.application.MeshCommandFacade
import org.springframework.stereotype.Service
import java.util.*

@Transactional
@Service
class Object3DCommandFacade(
  private val meshCommandFacade: MeshCommandFacade,
  private val groupCommandService: GroupCommandService,
  private val object3DCommandPort: Object3DCommandPort
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

  fun update(children: List<Object3DDTO>): List<Object3DDTO> {
    TODO()
  }

  
  fun deleteAll() {
    meshCommandFacade.deleteAll()
    object3DCommandPort.deleteAll().getOrThrow()
  }


}
