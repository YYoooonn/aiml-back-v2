package org.aiml.user.adapter.outgoing.persistence.entity

import jakarta.persistence.*
import org.aiml.libs.common.entity.BaseEntity

@Entity
@Table(name = "users")
data class UserEntity(
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,

  val username: String,
  val password: String,
  val email: String,
  val firstName: String? = null,
  val lastName: String? = null,
  val imgAddress: String? = null,

  @OneToMany(mappedBy = "user", cascade = [(CascadeType.ALL)], orphanRemoval = true)
  val userProjects: List<UserProjectEntity> = mutableListOf(),

  ) : BaseEntity()
