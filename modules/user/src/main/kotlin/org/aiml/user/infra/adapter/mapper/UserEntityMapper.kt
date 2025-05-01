package org.aiml.user.infra.adapter.mapper

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.aiml.user.domain.model.User
import org.aiml.user.infra.persistence.entity.UserEntity
import org.springframework.stereotype.Component

@Component
class UserEntityMapper(
  @PersistenceContext(unitName = "userPersistenceUnit")
  private val entityManager: EntityManager
) {
  fun toEntityRef(user: User): UserEntity {
    TODO()
  }
}
