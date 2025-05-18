package org.aiml.user.infra.adapter.mapper

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.aiml.user.domain.model.User
import org.aiml.user.infra.persistence.entity.UserEntity
import org.springframework.stereotype.Component

import org.aiml.libs.infra.mysql.BeanNames

@Component
class UserEntityMapper(
  @PersistenceContext(unitName = BeanNames.PERSISTENCE_UNIT)
  private val entityManager: EntityManager
) {
  fun toEntityRef(user: User): UserEntity {
    TODO()
  }
}
