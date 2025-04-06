package org.aiml.libs.common.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity {

  @Column(nullable = false, updatable = false)
  var createdAt: LocalDateTime = LocalDateTime.now()

  @Column(nullable = false, updatable = false)
  var updatedAt: LocalDateTime = LocalDateTime.now()

  @PrePersist
  fun onCreate() {
    val now = LocalDateTime.now()
    createdAt = now
    updatedAt = now
  }

  @PreUpdate
  fun onUpdate() {
    updatedAt = LocalDateTime.now()
  }
}
