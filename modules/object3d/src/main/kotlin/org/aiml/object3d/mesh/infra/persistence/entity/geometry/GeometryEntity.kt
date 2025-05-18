package org.aiml.object3d.mesh.infra.persistence.entity.geometry

import java.util.*
import jakarta.persistence.*
import org.aiml.libs.common.entity.BaseEntity
import org.aiml.object3d.mesh.domain.model.geometry.Face
import org.aiml.object3d.mesh.domain.model.geometry.Geometry
import org.aiml.object3d.mesh.domain.model.geometry.Vertex

@Table(name = "geometry")
@Entity
data class GeometryEntity(
  @Id
  @Column(name = "id")
  val id: UUID, // 받아서 사용, vertex face 에서 geometryId를 가지고 있어야 하기에

  @Column(name = "mesh_id", nullable = false)
  val meshId: UUID,

  @Column(nullable = true)
  val name: String,

  ) : BaseEntity() {

  companion object {
    fun from(domain: Geometry): GeometryEntity = GeometryEntity(
      id = domain.id,
      meshId = domain.meshId,
      name = domain.name
    )
  }

  fun toDomain(
    vertices: List<Vertex>,
    faces: List<Face>
  ): Geometry {
    return Geometry(
      id = id,
      name = name,
      vertices = vertices,
      faces = faces,
      meshId = meshId,
      createdAt = createdAt,
      updatedAt = updatedAt,
    )
  }
}
