package org.aiml.geometry.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Mesh(
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,
  val name: String,
)
