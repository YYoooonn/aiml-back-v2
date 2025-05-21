package org.aiml.object3d.mesh.infra.persistence.entity.geometry

import jakarta.persistence.*
import org.aiml.object3d.mesh.domain.model.geometry.Face
import org.aiml.object3d.mesh.domain.model.geometry.FaceVertex
import java.io.Serializable
import java.util.*

@Entity
@IdClass(FaceId::class)
@Table(name = "face")
data class FaceEntity(
  @Id
  @Column(name = "geometry_id", nullable = false)
  val geometryId: UUID,

  @Column(name = "face_index", nullable = false)
  val index: Int
) {
  companion object {
    fun from(domain: Face, geoId: UUID) = FaceEntity(
//      id = domain.id,
      geometryId = geoId,
      index = domain.index
    )
  }

  fun toDomain(vertices: List<FaceVertex>) = Face(
    geometryId = geometryId,
    index = index,
    vertices = vertices
  )
}

data class FaceId(
  val geometryId: UUID = UUID(0, 0),
  val index: Int = 0
) : Serializable
