package org.aiml.object3d.base.application.dto

import org.aiml.object3d.mesh.domain.model.geometry.Face
import org.aiml.object3d.mesh.domain.model.geometry.FaceVertex
import org.aiml.object3d.mesh.domain.model.geometry.Geometry
import org.aiml.object3d.mesh.domain.model.geometry.Vertex
import java.util.UUID

data class GeometryDTO(
  val id: UUID = UUID.randomUUID(),
  val name: String = "untitled geometry",
  val vertices: List<VertexDTO>,
  val faces: List<List<Int>>
) {
  companion object {
    fun from(geometry: Geometry): GeometryDTO {
      val faces = geometry.faces.map { face ->
        face.vertices.map { fv -> fv.vertexIndex }
      }
      val vertices = geometry.vertices.map { VertexDTO(it.x, it.y, it.z) }
      return GeometryDTO(
        id = geometry.id,
        name = geometry.name,
        vertices = vertices,
        faces = faces
      )
    }
  }

  fun toDomain(meshId: UUID): Geometry {
    return Geometry(
      id = id,
      meshId = meshId,
      name = name,
      vertices = toVertices(id, vertices),
      faces = toFaces(id, faces)
    )
  }

  private fun toVertices(geoId: UUID, vertices: List<VertexDTO>) =
    vertices.mapIndexed { index, v -> v.toDomain(geoId, index) }

  private fun toFaceVertices(indices: List<Int>): List<FaceVertex> =
    indices.mapIndexed { i, vIndex -> FaceVertex(vertexIndexOrder = i, vertexIndex = vIndex) }

  private fun toFaces(geoId: UUID, faces: List<List<Int>>) =
    faces.mapIndexed { index, indices ->
      Face(
        geometryId = geoId,
        index = index,
        vertices = toFaceVertices(indices)
      )
    }
}

data class VertexDTO(val x: Float, val y: Float, val z: Float) {
  companion object {
    fun from(x: Float, y: Float, z: Float): VertexDTO =
      VertexDTO(
        x = x,
        y = y,
        z = z
      )
  }

  fun toDomain(id: UUID, index: Int): Vertex = Vertex(
    geometryId = id,
    index = index,
    x = x,
    y = y,
    z = z
  )
}
