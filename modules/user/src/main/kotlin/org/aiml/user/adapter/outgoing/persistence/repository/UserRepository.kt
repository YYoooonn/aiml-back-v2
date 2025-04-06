package org.aiml.user.adapter.outgoing.persistence.repository

import org.aiml.user.adapter.outgoing.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
}
