package org.aiml.object3d.mesh.infra.persistence.entity.geometry

import jakarta.persistence.*
import org.aiml.object3d.mesh.domain.model.geometry.FaceVertex
import java.io.Serializable
import java.util.*

@Entity
@IdClass(FaceVertexId::class)
data class FaceVertexEntity(
  @Id
  @Column(name = "geometry_id", nullable = false)
  val geometryId: UUID,

  @Id
  @Column(name = "face_index", nullable = false)
  val faceIndex: Int,

  @Id val vertexIndexOrder: Int,

  @Column(nullable = false)
  val vertexRefIndex: Int
) {
  companion object {
    fun from(constructor: FVEntityConstructor): FaceVertexEntity {
      return FaceVertexEntity(
        faceIndex = constructor.faceIndex,
        geometryId = constructor.geometryId,
        vertexIndexOrder = constructor.order,
        vertexRefIndex = constructor.domain.vertexIndex
      )
    }
  }

  fun toDomain(): FaceVertex {
    return FaceVertex(
      faceIndex = faceIndex,
      vertexIndexOrder = vertexIndexOrder,
      vertexIndex = vertexRefIndex
    )
  }
}

data class FaceVertexId(
  val geometryId: UUID = UUID(0, 0),
  val faceIndex: Int = 0,
  val vertexIndexOrder: Int = 0
) : Serializable

data class FVEntityConstructor(
  val faceIndex: Int,
  val geometryId: UUID,
  val order: Int,
  val domain: FaceVertex
)
