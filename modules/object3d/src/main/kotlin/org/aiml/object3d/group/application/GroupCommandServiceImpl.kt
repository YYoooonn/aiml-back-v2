package org.aiml.object3d.group.application

import org.aiml.object3d.base.application.dto.GroupDTO
import org.aiml.object3d.group.domain.port.inbound.GroupCommandService
import org.aiml.object3d.group.domain.port.outbound.GroupCommandPort
import org.springframework.stereotype.Service
import java.util.*

@Service
class GroupCommandServiceImpl(
  private val groupCommandPort: GroupCommandPort
) : GroupCommandService {
  override fun create(dto: GroupDTO): GroupDTO {
    val group = groupCommandPort.save(dto.toDomain()).getOrThrow()
    return GroupDTO.from(group, dto.children)
  }

  override fun delete(id: UUID) {
    groupCommandPort.delete(id).getOrThrow()
  }

  override fun update(dto: GroupDTO): GroupDTO {
    val updated = groupCommandPort.update(dto.toDomain()).getOrThrow()
    return GroupDTO.from(updated, dto.children)
  }
}
