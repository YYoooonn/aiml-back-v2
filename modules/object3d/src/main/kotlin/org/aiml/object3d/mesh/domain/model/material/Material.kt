package org.aiml.object3d.mesh.domain.model.material

import java.util.*

data class Material(
  val id: UUID,
  val meshId: UUID,
  val name: String = "material",
  val color: String, // Hex code like "#ffffff"
  val opacity: Float,
  val transparent: Boolean = false,
  val textureMapUrl: String? = null
)
