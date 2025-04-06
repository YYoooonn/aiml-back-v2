package org.aiml.user.adapter.outgoing.persistence.mapper

import org.aiml.user.adapter.outgoing.persistence.entity.UserEntity
import org.aiml.user.core.domain.model.User
import org.springframework.stereotype.Component

@Component
class UserMapper {

  fun toDomain(entity: UserEntity): User {
    return User(
      id = entity.id,
      username = entity.username,
      email = entity.email,
      firstName = entity.firstName,
      lastName = entity.lastName,
      password = entity.password,
      imgAddress = entity.imgAddress,
      updatedAt = entity.updatedAt,
      createdAt = entity.createdAt
    )
  }

  fun toEntity(user: User): UserEntity {
    return UserEntity(
      id = user.id,
      username = user.username,
      email = user.email,
      firstName = user.firstName,
      lastName = user.lastName,
      password = user.password,
      imgAddress = user.imgAddress,
    )
  }
}
