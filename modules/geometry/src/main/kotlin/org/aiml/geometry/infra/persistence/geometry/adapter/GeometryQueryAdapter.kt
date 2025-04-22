package org.aiml.geometry.infra.persistence.geometry.adapter

import com.querydsl.jpa.impl.JPAQueryFactory
import org.aiml.geometry.domain.geometry.model.*
import org.aiml.geometry.infra.persistence.geometry.entity.*
import org.aiml.geometry.domain.geometry.port.outbound.GeometryQueryPort
import org.aiml.geometry.infra.persistence.geometry.repository.GeometryRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class GeometryQueryAdapter(
  private val geometryRepository: GeometryRepository,
  @Qualifier("geometryQueryFactory")
  private val queryFactory: JPAQueryFactory,
) : GeometryQueryPort {

  override fun findById(geometryId: UUID): Result<Geometry> = runCatching {
    val geometryEntity = geometryRepository.findById(geometryId)
      .orElseThrow { NoSuchElementException("Geometry $geometryId not found") }

    val vertices = queryVerticesByGeometryId(geometryId)
      .map { it.toDomain() }

    val faces = buildFaces(geometryId)

    geometryEntity.toDomain(vertices, faces)
  }


  override fun findFacesByGeometryId(geometryId: UUID): Result<List<Face>> = runCatching {
    val faceEntities = queryFacesByGeometryId(geometryId)
    val faceIds = faceEntities.map { it.id }
    val faceVertexEntities = queryFaceVerticesByFaceIds(faceIds)

    val faceVertexMap = faceVertexEntities.groupBy { it.faceId }

    faceEntities.map { face ->
      val vertices = faceVertexMap[face.id]?.map { it.toDomain() } ?: emptyList()
      face.toDomain(vertices)
    }
  }


  override fun findVerticesByGeometryId(geometryId: UUID): Result<List<Vertex>> = runCatching {
    queryVerticesByGeometryId(geometryId)
      .map { it.toDomain() }
  }


  // ------------------- PRIVATE HELPERS -------------------

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
