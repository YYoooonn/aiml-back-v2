package org.aiml.object3d.mesh.infra.adapter.query

import org.aiml.object3d.mesh.infra.persistence.entity.geometry.*
import org.aiml.object3d.mesh.domain.model.geometry.*
import org.aiml.object3d.mesh.infra.persistence.repository.GeometryRepository
import org.aiml.object3d.mesh.domain.port.outbound.query.GeometryQueryPort

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.util.UUID


@Component
class GeometryQueryAdapter(
  private val geometryRepository: GeometryRepository,
  @Qualifier("meshQueryFactory")
  private val queryFactory: JPAQueryFactory,
) : GeometryQueryPort {

  override fun findByIds(ids: List<UUID>): Result<List<Geometry>> = runCatching {
    if (ids.isEmpty()) return@runCatching emptyList()

    // 1. GeometryEntity 조회
    val geometryEntities = geometryRepository.findAllById(ids)

    // 2. VertexEntity 조회
    val vertices = queryVerticesByGeometryIds(ids)

    // 3. FaceEntity 조회
    val faces = queryFacesByGeometryIds(ids)

    // 4. FaceVertexEntity 조회
    val faceVertexEntities = queryFaceVerticesByFaceIds(faces.map { it.id })

    // 5. Grouping
    val verticesMap = vertices.groupBy { it.geometryId }
    val facesMap = faces.groupBy { it.geometryId }
    val faceVerticesMap = faceVertexEntities.groupBy { it.faceId }

    // 6. 조립
    geometryEntities.map { geometryEntity ->
      val geometryId = geometryEntity.id
      val geometryVertices = verticesMap[geometryId]?.map { it.toDomain() } ?: emptyList()
      val geometryFaces = facesMap[geometryId]?.map { faceEntity ->
        val faceVertices = faceVerticesMap[faceEntity.id]?.map { it.toDomain() } ?: emptyList()
        faceEntity.toDomain(faceVertices)
      } ?: emptyList()

      geometryEntity.toDomain(geometryVertices, geometryFaces)
    }
  }.onFailure {
    // TODO: add logger
  }

  override fun findById(geometryId: UUID): Result<Geometry> = runCatching {
    val geometryEntity = geometryRepository.findById(geometryId)
      .orElseThrow { NoSuchElementException("Geometry $geometryId not found") }
    val vertices = queryVerticesByGeometryId(geometryId)
      .map { it.toDomain() }
    val faces = buildFaces(geometryId)
    geometryEntity.toDomain(vertices, faces)
  }.onFailure {
    // TODO add logger
  }

  fun findFacesByGeometryId(geometryId: UUID): Result<List<Face>> = runCatching {
    val faceEntities = queryFacesByGeometryId(geometryId)
    val faceIds = faceEntities.map { it.id }
    val faceVertexEntities = queryFaceVerticesByFaceIds(faceIds)

    val faceVertexMap = faceVertexEntities.groupBy { it.faceId }

    faceEntities.map { face ->
      val vertices = faceVertexMap[face.id]?.map { it.toDomain() } ?: emptyList()
      face.toDomain(vertices)
    }
  }

  fun findVerticesByGeometryId(geometryId: UUID): Result<List<Vertex>> =
    runCatching {
      queryVerticesByGeometryId(geometryId)
        .map { it.toDomain() }
    }


  // ------------------- PRIVATE HELPERS -------------------

  private fun queryVerticesByGeometryIds(geometryIds: List<UUID>): List<VertexEntity> {
    val qVertex = QVertexEntity.vertexEntity
    return queryFactory
      .selectFrom(qVertex)
      .where(qVertex.geometryId.`in`(geometryIds))
      .orderBy(qVertex.geometryId.asc(), qVertex.index.asc())
      .fetch()
  }

  private fun queryFacesByGeometryIds(geometryIds: List<UUID>): List<FaceEntity> {
    val qFace = QFaceEntity.faceEntity
    return queryFactory
      .selectFrom(qFace)
      .where(qFace.geometryId.`in`(geometryIds))
      .orderBy(qFace.geometryId.asc(), qFace.index.asc())
      .fetch()
  }

  private fun queryVerticesByGeometryId(geometryId: UUID): List<VertexEntity> {
    val qVertex = QVertexEntity.vertexEntity
    return queryFactory
      .selectFrom(qVertex)
      .where(qVertex.geometryId.eq(geometryId))
      .orderBy(qVertex.index.asc())
      .fetch()
  }

  private fun queryFacesByGeometryId(geometryId: UUID): List<FaceEntity> {
    val qFace = QFaceEntity.faceEntity
    return queryFactory
      .selectFrom(qFace)
      .where(qFace.geometryId.eq(geometryId))
      .orderBy(qFace.index.asc())
      .fetch()
  }

  private fun queryFaceVerticesByFaceIds(faceIds: List<Long>): List<FaceVertexEntity> {
    if (faceIds.isEmpty()) return emptyList()
    val qFaceVertex = QFaceVertexEntity.faceVertexEntity
    return queryFactory
      .selectFrom(qFaceVertex)
      .where(qFaceVertex.faceId.`in`(faceIds))
      .orderBy(
        qFaceVertex.faceId.asc(),
        qFaceVertex.vertexIndexOrder.asc()
      )
      .fetch()
  }

  private fun buildFaces(geometryId: UUID): List<Face> {
    val faceEntities = queryFacesByGeometryId(geometryId)
    if (faceEntities.isEmpty()) return emptyList()

    val faceVertexEntities = queryFaceVerticesByFaceIds(faceEntities.map { it.id })
    val fvMap = faceVertexEntities.groupBy { it.faceId }

    return faceEntities.map { faceEntity ->
      val faceVertices = fvMap[faceEntity.id]?.map { it.toDomain() } ?: emptyList()
      faceEntity.toDomain(faceVertices)
    }
  }
}
