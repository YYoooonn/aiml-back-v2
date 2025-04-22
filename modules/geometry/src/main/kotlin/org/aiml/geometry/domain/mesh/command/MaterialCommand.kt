package org.aiml.geometry.domain.mesh.command

import java.util.*

data class CreateMaterialCommand(
  val name: String? = null,
  val color: String = "#ffffff",
  val opacity: Float = 1.0f,
  val transparent: Boolean = false,
  val textureMapUrl: String? = null
)

data class UpdateMaterialCommand(
  val id: UUID,
  val name: String? = null,
  val color: String? = null,
  val opacity: Float? = null,
  val transparent: Boolean? = null,
  val textureMapUrl: String? = null
)
