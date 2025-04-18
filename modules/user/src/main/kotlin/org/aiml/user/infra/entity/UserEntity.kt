package org.aiml.user.infra.entity

import jakarta.persistence.*
import org.aiml.libs.common.entity.BaseEntity
import org.aiml.user.domain.model.User
import java.util.UUID

@Entity
@Table(name = "users")
data class UserEntity(
  @Id
  var id: UUID,

  @Column(nullable = false, unique = true)
  val username: String,

  @Column(nullable = false)
  val password: String,

  @Column(nullable = false, unique = true)
  val email: String,

  @Enumerated(EnumType.STRING)
  val role: User.Role? = User.Role.USER,

  @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
  val profile: UserProfileEntity? = null

) : BaseEntity() {
  companion object {
    fun from(domain: User): UserEntity {
      return UserEntity(
        id = domain.id,
        username = domain.username,
        email = domain.email,
        password = domain.encryptedPassword,
        role = domain.role
      )
    }
  }

  fun toDomain(): User {
    return User(
      id = id,
      username = username,
      encryptedPassword = password,
      email = email,
      role = role,
      createdAt = createdAt
    )
  }
}
