package org.aiml.object3d.mesh.infra.adapter.query

import com.querydsl.jpa.impl.JPAQueryFactory
import org.aiml.object3d.mesh.domain.model.Mesh
import org.aiml.object3d.mesh.domain.port.outbound.query.MeshQueryPort
import org.aiml.object3d.mesh.infra.persistence.entity.MeshEntity
import org.aiml.object3d.mesh.infra.persistence.entity.QMeshEntity
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.util.*

@Component
class MeshQueryAdapter(
  @Qualifier("meshQueryFactory")
  private val queryFactory: JPAQueryFactory,
) : MeshQueryPort {

  override fun findBySceneId(sceneId: UUID): Result<List<Mesh>> = runCatching {
    queryMeshesBySceneId(sceneId).map { it.toDomain() }
  }

  override fun findById(id: UUID): Result<Mesh> = runCatching {
    findByIds(listOf(id))
      .getOrThrow()
      .firstOrNull()
      ?: throw NoSuchElementException("Mesh $id not found")
  }

  fun findByIds(ids: List<UUID>): Result<List<Mesh>> = runCatching {
    if (ids.isEmpty()) return@runCatching emptyList()
    queryMeshesByIds(ids).map { it.toDomain() }
  }

  // --------------------------
  private fun queryMeshesBySceneId(sceneId: UUID): List<MeshEntity> {
    val qMesh = QMeshEntity.meshEntity
    return queryFactory
      .selectFrom(qMesh)
      .where(qMesh.sceneId.eq(sceneId))
      .fetch()
  }

  private fun queryMeshesByIds(meshIds: List<UUID>): List<MeshEntity> {
    val qMesh = QMeshEntity.meshEntity
    return queryFactory
      .selectFrom(qMesh)
      .where(qMesh.id.`in`(meshIds))
      .fetch()
  }
}
