package org.aiml.geometry.infra.persistence.mesh.entity

import jakarta.persistence.*
import org.aiml.geometry.domain.mesh.model.*
import org.aiml.libs.common.entity.BaseEntity
import java.util.*


@Entity
@Table(name = "mesh")
class MeshEntity(

  @Id
  val id: UUID = UUID.randomUUID(),

  val geometryId: UUID,

  val materialId: UUID,

  val name: String? = null,

  @Embedded
  val transform: TransformEmbeddable = TransformEmbeddable(),

  @ManyToOne
  @JoinColumn(name = "mesh_group_id")
  val meshGroup: MeshGroupEntity? = null

) : BaseEntity() {
  fun toDomain(): Mesh = Mesh(
    id = id,
    geometryId = geometryId,
    materialId = materialId,
    transform = transform.toDomain(),
    name = name,
    createdAt = createdAt,
    updatedAt = updatedAt
  )

  companion object {
    fun from(mesh: Mesh): MeshEntity = MeshEntity(
      id = mesh.id,
      name = mesh.name,
      geometryId = mesh.geometryId,
      materialId = mesh.materialId,
      transform = TransformEmbeddable.from(mesh.transform),
    )
  }
}
