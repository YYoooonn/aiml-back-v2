package org.aiml.object3d.group.domain.port.inbound

import org.aiml.object3d.base.application.dto.GroupDTO
import java.util.*

interface GroupCommandService {
  fun create(dto: GroupDTO): GroupDTO
  fun update(dto: GroupDTO): GroupDTO
  fun delete(id: UUID)
}
