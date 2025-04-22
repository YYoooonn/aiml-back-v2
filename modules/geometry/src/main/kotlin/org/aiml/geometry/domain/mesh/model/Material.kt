package org.aiml.geometry.domain.mesh.model

import org.aiml.geometry.domain.mesh.command.CreateMaterialCommand
import org.aiml.geometry.domain.mesh.command.UpdateMaterialCommand
import java.util.*

data class Material(
  val id: UUID = UUID.randomUUID(),
  val name: String? = null,
  val color: String, // Hex code like "#ffffff"
  val opacity: Float,
  val transparent: Boolean = false,
  val textureMapUrl: String? = null
) {
  companion object {
    fun build(command: CreateMaterialCommand): Material = Material(
      name = command.name,
      color = command.color,
      opacity = command.opacity,
      transparent = command.transparent,
      textureMapUrl = command.textureMapUrl
    )
  }

  fun update(command: UpdateMaterialCommand): Material = this.copy(
    name = command.name ?: name,
    color = command.color ?: color,
    opacity = command.opacity ?: opacity,
    transparent = command.transparent ?: transparent,
    textureMapUrl = command.textureMapUrl ?: textureMapUrl
  )

}
