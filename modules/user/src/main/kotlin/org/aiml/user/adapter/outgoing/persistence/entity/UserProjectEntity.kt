package org.aiml.user.adapter.outgoing.persistence.entity

import org.aiml.user.core.domain.model.*
import jakarta.persistence.*
import org.aiml.libs.common.entity.BaseEntity

@Entity
@Table(name = "user_project")
data class UserProjectEntity(
  @EmbeddedId
  val id: UserProjectId,

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("userId")
  val user: UserEntity,

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("projectId")
  val project: ProjectEntity,

  @Enumerated(EnumType.STRING)
  val role: UserProjectRole,

  @Enumerated(EnumType.STRING)
  val permission: UserProjectPermission
) : BaseEntity()
