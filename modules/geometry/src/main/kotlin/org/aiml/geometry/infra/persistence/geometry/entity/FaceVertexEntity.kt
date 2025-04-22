package org.aiml.geometry.infra.persistence.geometry.entity

import jakarta.persistence.*
import org.aiml.geometry.domain.geometry.model.FaceVertex
import java.io.Serializable

@Entity
@IdClass(FaceVertexId::class)
data class FaceVertexEntity(
  @Id val faceId: Long,
  @Id val vertexIndexOrder: Int,

  @Column(nullable = false)
  val vertexRefIndex: Int
) {
  companion object {
    fun from(constructor: FVEntityConstructor): FaceVertexEntity {
      return FaceVertexEntity(
        faceId = constructor.faceId,
        vertexIndexOrder = constructor.order,
        vertexRefIndex = constructor.domain.vertexIndex
      )
    }
  }

  fun toDomain(): FaceVertex {
    return FaceVertex(
      faceId = faceId,
      vertexIndexOrder = vertexIndexOrder,
      vertexIndex = vertexRefIndex
    )
  }
}

data class FaceVertexId(
  val faceId: Long = 0,
  val vertexIndexOrder: Int = 0
) : Serializable

data class FVEntityConstructor(
  val faceId: Long,
  val order: Int,
  val domain: FaceVertex
)
