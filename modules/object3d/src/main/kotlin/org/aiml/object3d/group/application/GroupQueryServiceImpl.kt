package org.aiml.object3d.group.application

import org.aiml.object3d.base.application.dto.GroupDTO
import org.aiml.object3d.group.domain.port.inbound.GroupQueryService
import org.aiml.object3d.group.domain.port.outbound.GroupQueryPort
import org.springframework.stereotype.Service
import java.util.*

@Service
class GroupQueryServiceImpl(
  private val groupQueryPort: GroupQueryPort
) : GroupQueryService {
  override fun findBySceneId(sceneId: UUID): Result<List<GroupDTO>> = runCatching {
    // return with empty children
    groupQueryPort.findBySceneId(sceneId).getOrThrow().map { GroupDTO.from(it) }
  }

  override fun findById(id: UUID): Result<GroupDTO> = runCatching {
    groupQueryPort.findById(id).getOrThrow().let { GroupDTO.from(it) }
  }
}
