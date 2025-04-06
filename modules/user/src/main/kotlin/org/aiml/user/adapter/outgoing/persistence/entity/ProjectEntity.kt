package org.aiml.user.adapter.outgoing.persistence.entity

import jakarta.persistence.*
import org.aiml.libs.common.entity.BaseEntity
import java.time.LocalDateTime

@Entity
@Table(name = "project")
data class ProjectEntity(
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,

  val title: String,
  val subtitle: String? = null,
  val description: String? = null,

  ) : BaseEntity()
