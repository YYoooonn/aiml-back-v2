package org.aiml.geometry.infra.persistence.geometry.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Table
import jakarta.persistence.GenerationType
import org.aiml.geometry.domain.geometry.model.Face
import org.aiml.geometry.domain.geometry.model.FaceVertex
import java.util.*

@Entity
@Table(
  name = "face",
  indexes = [Index(name = "idx_face_geometry", columnList = "geometry_id")]
)
data class FaceEntity(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0L,

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
    id = id,
    geometryId = geometryId,
    index = index,
    vertices = vertices
  )
}
