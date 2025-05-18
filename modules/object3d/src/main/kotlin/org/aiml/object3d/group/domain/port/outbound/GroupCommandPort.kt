package org.aiml.object3d.group.domain.port.outbound

import org.aiml.object3d.group.domain.model.Group
import java.util.UUID

interface GroupCommandPort {
  fun save(group: Group): Result<Group>
  fun update(group: Group): Result<Group>
  fun delete(id: UUID): Result<Unit>
}
