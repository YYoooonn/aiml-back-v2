package org.aiml.user.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,
  var username: String,
)
