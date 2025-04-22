package org.aiml.geometry.infra.persistence.mesh.entity

import jakarta.persistence.*
import org.aiml.geometry.domain.mesh.model.Material
import org.aiml.libs.common.entity.BaseEntity
import java.util.*

@Entity
@Table(name = "materials")
data class MaterialEntity(
  @Id val id: UUID = UUID.randomUUID(),

  val name: String? = null,

  val color: String,

  val opacity: Float,

  val transparent: Boolean,

  val textureMapUrl: String? = null
) : BaseEntity() {
  fun toDomain(): Material = Material(
    id = id,
    name = name,
    color = color,
    opacity = opacity,
    transparent = transparent,
    textureMapUrl = textureMapUrl
  )

  companion object {
    fun from(material: Material): MaterialEntity = MaterialEntity(
      id = material.id,
      name = material.name,
      color = material.color,
      opacity = material.opacity,
      transparent = material.transparent,
      textureMapUrl = material.textureMapUrl
    )
  }
}
