package org.aiml.object3d.base.application.facade

import org.aiml.object3d.base.application.dto.Object3DDTO
import org.aiml.object3d.group.domain.port.inbound.GroupQueryService
import org.aiml.object3d.mesh.domain.port.inbound.MeshQueryService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class Object3DQueryFacade(
  private val groupQueryService: GroupQueryService,
  private val meshQueryService: MeshQueryService
) {

  fun getObjectTree(sceneId: UUID): List<Object3DDTO> {
    val groups = groupQueryService.findBySceneId(sceneId).getOrThrow()
    val meshes = meshQueryService.findBySceneId(sceneId)

    val objects = mutableListOf<Object3DDTO>()
    objects.addAll(meshes)
    objects.addAll(groups)

    return Object3DTreeBuilder.build(objects)
  }
}
