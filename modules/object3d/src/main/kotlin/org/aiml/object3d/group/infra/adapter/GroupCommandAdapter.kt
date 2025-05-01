package org.aiml.object3d.group.infra.adapter

import jakarta.transaction.Transactional
import org.aiml.object3d.base.domain.port.outbound.Object3DQueryPort
import org.aiml.object3d.base.infra.adapter.mapper.Object3DEntityMapper
import org.aiml.object3d.group.domain.model.Group
import org.aiml.object3d.group.domain.port.outbound.GroupCommandPort
import org.aiml.object3d.group.infra.persistence.entity.GroupEntity
import org.aiml.object3d.group.infra.persistence.repository.GroupRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class GroupCommandAdapter(
  private val groupRepository: GroupRepository,
  private val object3DQueryPort: Object3DQueryPort,
  private val objectMapper: Object3DEntityMapper
) : GroupCommandPort {

  override fun save(group: Group): Result<Group> = runCatching {
    val parent = group.parentId?.let { object3DQueryPort.findById(it).getOrThrow() }
    groupRepository.save(GroupEntity.from(group, parent?.let { objectMapper.toEntityRef(it) })).toDomain()
  }

  override fun update(group: Group): Result<Group> = runCatching {
    // FIXME  update
    save(group).getOrThrow()
  }

  override fun delete(id: UUID): Result<Unit> = runCatching {
    groupRepository.deleteById(id)
  }
}
