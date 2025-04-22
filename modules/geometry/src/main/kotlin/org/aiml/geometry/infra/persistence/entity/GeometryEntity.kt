package org.aiml.geometry.infra.persistence.entity

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
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  val id: UUID = UUID.randomUUID(),

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
