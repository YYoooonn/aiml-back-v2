package org.aiml.object3d.mesh.infra.persistence.entity

import jakarta.persistence.*
import org.aiml.object3d.mesh.domain.model.material.Material
import org.aiml.libs.common.entity.BaseEntity
import java.util.*

@Entity
@Table(name = "materials")
data class MaterialEntity(
  @Id val id: UUID = UUID.randomUUID(),

  @Column(name = "mesh_id", nullable = false)
  val meshId: UUID,

  val name: String = "untitled material",

  val color: String,

  val opacity: Float,

  val transparent: Boolean,

  val textureMapUrl: String? = null
) : BaseEntity() {
  fun toDomain(): Material = Material(
    id = id,
    meshId = meshId,
    name = name,
    color = color,
    opacity = opacity,
    transparent = transparent,
    textureMapUrl = textureMapUrl
  )

  companion object {
    fun from(material: Material): MaterialEntity = MaterialEntity(
      id = material.id,
      meshId = material.meshId,
      name = material.name,
      color = material.color,
      opacity = material.opacity,
      transparent = material.transparent,
      textureMapUrl = material.textureMapUrl
    )
  }
}
