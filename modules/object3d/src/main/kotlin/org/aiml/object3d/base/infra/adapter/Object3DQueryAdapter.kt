package org.aiml.object3d.base.infra.adapter

import com.querydsl.jpa.impl.JPAQueryFactory
import org.aiml.object3d.base.domain.model.Object3D
import org.aiml.object3d.base.domain.port.outbound.Object3DQueryPort
import org.aiml.object3d.base.infra.persistence.entity.QObject3DEntity
import org.aiml.object3d.base.infra.persistence.repository.Object3DRepository
import org.aiml.object3d.group.infra.persistence.entity.GroupEntity
import org.aiml.object3d.mesh.infra.persistence.entity.MeshEntity
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.util.*

@Component
class Object3DQueryAdapter(
  private val object3DRepository: Object3DRepository,
  @Qualifier("object3DQueryFactory")
  private val queryFactory: JPAQueryFactory
) : Object3DQueryPort {
  override fun findAllByIds(ids: List<UUID>): Result<List<Object3D>> = runCatching {
    object3DRepository.findAllById(ids).map {
      it.toDomain()
    }
  }

  override fun findById(id: UUID): Result<Object3D> = runCatching {
    object3DRepository.findById(id).get().toDomain()
  }

  override fun findAllBySceneId(sceneId: UUID): Result<List<Object3D>> = runCatching {
    object3DRepository.findAllBySceneId(sceneId).map { it.toDomain() }
  }

  override fun findAll(): Result<List<Object3D>> = runCatching {
    object3DRepository.findAll().map { it.toDomain() }
  }

  override fun findAllDescendantIds(rootId: UUID): Result<List<UUID>> = runCatching {
    val qObject3D = QObject3DEntity.object3DEntity
    val result = mutableListOf<UUID>()
    val queue = ArrayDeque<UUID>()
    queue.add(rootId)

    while (!queue.isEmpty()) {
      val currentId = queue.removeFirst()
      result.add(currentId)

      val childIds = queryFactory
        .select(qObject3D.id)
        .from(qObject3D)
        .where(qObject3D.parent.id.eq(currentId))
        .fetch()

      queue.addAll(childIds)
    }

    result
  }
}
