package org.aiml.geometry.application.dto

import org.aiml.geometry.domain.mesh.model.*
import java.util.*

data class MeshDTO(
  val id: UUID = UUID.randomUUID(),
  val name: String? = "",
  val geometry: GeometryDTO,
  val material: MaterialDTO,
  val position: Vector3DTO, // [x,y,z]
  val rotation: Vector3DTO, // [x,y,z] (radians
  val scale: Vector3DTO, // [x,y,z]
)

data class GeometryDTO(
  val vertices: List<Vector3DTO>,
  val faces: List<List<Int>>
)

data class MaterialDTO(
  val color: String,
  val opacity: Float,
  val transparent: Boolean,
  val map: String? = null
)

data class Vector3DTO(val x: Float, val y: Float, val z: Float) {
  companion object {
    fun from(vector: Vector3) = Vector3DTO(vector.x, vector.y, vector.z)
  }
}
