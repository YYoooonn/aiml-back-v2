package org.aiml.geometry.infra.persistence.entity

import jakarta.persistence.*
import java.io.Serializable

@Entity
@IdClass(org.aiml.geometry.infra.persistence.entity.FaceVertexId::class)
data class FaceVertexEntity(
  @Id val faceId: Long,
  @Id val vertexIndexOrder: Int,

  @Column(nullable = false)
  val vertexRefIndex: Int
)

data class FaceVertexId(
  val faceId: Long = 0,
  val vertexIndexOrder: Int = 0
) : Serializable
