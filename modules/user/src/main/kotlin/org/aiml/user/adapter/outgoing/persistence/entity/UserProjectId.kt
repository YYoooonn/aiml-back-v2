package org.aiml.user.adapter.outgoing.persistence.entity

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class UserProjectId(
  val userId: Long,
  val projectId: Long
) : Serializable
