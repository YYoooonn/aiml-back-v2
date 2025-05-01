package org.aiml.scene.domain.model

import java.time.LocalDateTime
import java.util.UUID

data class Scene(
  val id: UUID = UUID.randomUUID(),
  val projectId: UUID,
  val name: String = "untitled",
  val createdAt: LocalDateTime = LocalDateTime.now(),
  val updatedAt: LocalDateTime = LocalDateTime.now(),
)
