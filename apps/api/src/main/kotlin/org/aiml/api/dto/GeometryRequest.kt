package org.aiml.api.dto

data class GeometryRequest(
  val name: String?,
  val vertices: List<VertexRequest>,
  val faces: List<FaceRequest>
)

data class VertexRequest(val x: Double, val y: Double, val z: Double)

data class FaceRequest(val vertices: List<FaceVertexRequest>)

data class FaceVertexRequest(val vertexIndex: Int)
