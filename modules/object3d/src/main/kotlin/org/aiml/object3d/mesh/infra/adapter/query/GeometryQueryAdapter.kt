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

    // 4. FaceVertexEntity 조회 (geometryId 기준 조회)
    val faceVertexEntities = queryFaceVerticesByGeometryIds(ids)

    // 5. Grouping
    val verticesMap = vertices.groupBy { it.geometryId }
    val facesMap = faces.groupBy { it.geometryId }
    val faceVerticesMap = faceVertexEntities.groupBy { Pair(it.geometryId, it.faceIndex) }

//    // 6. Grouping
//    val verticesMap = vertices.groupBy { it.geometryId }
//    val facesMap = faces.groupBy { it.geometryId }
//    val faceVerticesMap = faceVertexEntities.groupBy { FaceId(it.geometryId, it.faceIndex) }

    // 7. 조립
    geometryEntities.map { geometryEntity ->
      val geometryId = geometryEntity.id
      val geometryVertices = verticesMap[geometryId]?.map { it.toDomain() } ?: emptyList()
      val geometryFaces = facesMap[geometryId]?.map { faceEntity ->
        val faceId = Pair(faceEntity.geometryId, faceEntity.index)
        val faceVertices = faceVerticesMap[faceId]?.map { it.toDomain() } ?: emptyList()
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
    TODO()
//    val faceEntities = queryFacesByGeometryId(geometryId)
//    val faceIds = faceEntities.map { it.id }
//    val faceVertexEntities = queryFaceVerticesByFaceIds(faceIds)
//
//    val faceVertexMap = faceVertexEntities.groupBy { it.faceId }
//
//    faceEntities.map { face ->
//      val vertices = faceVertexMap[face.id]?.map { it.toDomain() } ?: emptyList()
//      face.toDomain(vertices)
//    }
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

  private fun queryFaceVerticesByGeometryIds(geometryIds: List<UUID>): List<FaceVertexEntity> {
    if (geometryIds.isEmpty()) return emptyList()

    val qFaceVertex = QFaceVertexEntity.faceVertexEntity

    return queryFactory
      .selectFrom(qFaceVertex)
      .where(qFaceVertex.geometryId.`in`(geometryIds))
      .orderBy(
        qFaceVertex.geometryId.asc(),
        qFaceVertex.faceIndex.asc(),
        qFaceVertex.vertexIndexOrder.asc()
      )
      .fetch()
  }

  private fun buildFaces(geometryId: UUID): List<Face> {
    val faceEntities = queryFacesByGeometryId(geometryId)
    if (faceEntities.isEmpty()) return emptyList()

    val faceVertexEntities = queryFaceVerticesByGeometryIds(listOf(geometryId))
    val fvMap = faceVertexEntities.groupBy { Pair(it.geometryId, it.faceIndex) }

    return faceEntities.map { faceEntity ->
      val faceId = Pair(faceEntity.geometryId, faceEntity.index)
      val faceVertices = fvMap[faceId]?.map { it.toDomain() } ?: emptyList()
      faceEntity.toDomain(faceVertices)
    }
  }


  private fun queryFaceVerticesByFaceIds(faceIds: List<FaceId>): List<FaceVertexEntity> {
    TODO()
//    if (faceIds.isEmpty()) return emptyList()
//
//    val qFaceVertex = QFaceVertexEntity.faceVertexEntity
//
//    val tuples = faceIds.map {
//      Expressions.list(
//        Expressions.constant(it.geometryId),
//        Expressions.constant(it.index)
//      )
//    }
//
//    return queryFactory
//      .selectFrom(qFaceVertex)
//      .where(
//        Expressions.list(qFaceVertex.geometryId, qFaceVertex.faceIndex).`in`(tuples)
//      )
//      .orderBy(
//        qFaceVertex.geometryId.asc(),
//        qFaceVertex.faceIndex.asc(),
//        qFaceVertex.vertexIndexOrder.asc()
//      )
//      .fetch()
  }

}
