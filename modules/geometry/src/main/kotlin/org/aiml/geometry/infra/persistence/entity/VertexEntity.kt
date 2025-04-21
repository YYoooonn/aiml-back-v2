package org.aiml.geometry.infra.persistence.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(
  name = "vertex",
  indexes = [Index(name = "idx_vertex_geometry", columnList = "geometry_id")]
)
data class VertexEntity(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0L,

  @Column(name = "geometry_id", nullable = false)
  val geometryId: UUID,

  @Column(name = "vertex_index", nullable = false)
  val index: Int,

  @Column(nullable = false)
  val x: Double,

  @Column(nullable = false)
  val y: Double,

  @Column(nullable = false)
  val z: Double
)
