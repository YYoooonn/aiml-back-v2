package org.aiml.geometry.infra.persistence.geometry.entity

import java.util.*
import jakarta.persistence.*
import org.aiml.geometry.domain.geometry.model.Face
import org.aiml.geometry.domain.geometry.model.Geometry
import org.aiml.geometry.domain.geometry.model.Vertex
import org.aiml.libs.common.entity.BaseEntity

@Table(name = "geometry")
@Entity
data class GeometryEntity(
  @Id
  @Column(name = "id")
  val id: UUID, // 받아서 사용, vertex face 에서 geometryId를 가지고 있어야 하기에

  @Column(nullable = true)
  val name: String? = null,

  ) : BaseEntity() {

  companion object {
    fun from(domain: Geometry): GeometryEntity = GeometryEntity(
      id = domain.id,
      name = domain.name
    )
  }

  fun toDomain(vertices: List<Vertex>, faces: List<Face>): Geometry {
    return Geometry(
      id = id,
      name = name,
      vertices = vertices,
      faces = faces,
      createdAt = createdAt,
      updatedAt = updatedAt,
    )
  }
}
