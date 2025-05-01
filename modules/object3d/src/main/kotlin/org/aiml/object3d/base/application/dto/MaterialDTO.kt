package org.aiml.object3d.base.application.dto

import org.aiml.object3d.mesh.domain.model.material.Material
import java.util.UUID

data class MaterialDTO(
  val id: UUID = UUID.randomUUID(),
  val color: String,
  val name: String = "material",
  val opacity: Float,
  val transparent: Boolean,
  val map: String? = null
) {
  companion object {
    fun from(material: Material): MaterialDTO = MaterialDTO(
      id = material.id,
      name = material.name,
      color = material.color,
      opacity = material.opacity,
      transparent = material.transparent,
      map = material.textureMapUrl
    )
  }

  fun toDomain(meshId: UUID): Material = Material(
    id = id,
    meshId = meshId,
    name = name,
    opacity = opacity,
    transparent = transparent,
    color = color,
    textureMapUrl = map
  )
}
