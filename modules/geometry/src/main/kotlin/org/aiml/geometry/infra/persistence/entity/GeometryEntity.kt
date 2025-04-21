package org.aiml.geometry.infra.persistence.entity

import java.util.*
import jakarta.persistence.*
import org.aiml.libs.common.entity.BaseEntity

@Table(
  name = "geometry",
  indexes = [
    Index(name = "idx_geometry_name", columnList = "name")
  ]
)
@Entity
data class GeometryEntity(
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  val id: UUID = UUID.randomUUID(),

  @Column(nullable = false)
  val name: String,

  ) : BaseEntity()
