package org.aiml.object3d.group.infra.adapter

import com.querydsl.jpa.impl.JPAQueryFactory
import org.aiml.object3d.group.domain.model.Group
import org.aiml.object3d.group.domain.port.outbound.GroupQueryPort
import org.aiml.object3d.group.infra.persistence.entity.GroupEntity
import org.aiml.object3d.group.infra.persistence.entity.QGroupEntity
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.util.*

@Component
class GroupQueryAdapter(
  @Qualifier("groupQueryFactory")
  private val queryFactory: JPAQueryFactory,
) : GroupQueryPort {

  override fun findBySceneId(sceneId: UUID): Result<List<Group>> = runCatching {
    queryGroupsBySceneId(sceneId).map { it.toDomain() }
  }

  override fun findById(id: UUID): Result<Group> = runCatching {
    findByIds(listOf(id))
      .getOrThrow()
      .firstOrNull()
      ?: throw NoSuchElementException("Mesh $id not found")
  }

  fun findByIds(ids: List<UUID>): Result<List<Group>> = runCatching {
    if (ids.isEmpty()) return@runCatching emptyList()
    queryGroupsByIds(ids).map { it.toDomain() }
  }

  // ------------------

  private fun queryGroupsByIds(ids: List<UUID>): List<GroupEntity> {
    val qGroup = QGroupEntity.groupEntity
    return queryFactory.selectFrom(qGroup).where(qGroup.id.`in`(ids)).fetch()
  }

  private fun queryGroupsBySceneId(sceneId: UUID): List<GroupEntity> {
    val qGroup = QGroupEntity.groupEntity
    return queryFactory.selectFrom(qGroup).where(qGroup.sceneId.eq(sceneId)).fetch()
  }
}
