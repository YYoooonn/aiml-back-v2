package org.aiml.project_user.infra.persistence

import jakarta.persistence.*
import org.aiml.project_user.domain.model.ProjectUser
import org.aiml.project_user.domain.model.ProjectUserRole
import java.util.*

@Entity
@Table(
  name = "project_user",
  uniqueConstraints = [UniqueConstraint(columnNames = ["project_id", "user_id"])],
  indexes = [
    Index(name = "idx_user_project_role", columnList = "user_id, project_id, role"),
    Index(name = "idx_project_user_role", columnList = "project_id, user_id, role")
  ]
)
data class ProjectUserEntity(
  @Id val id: UUID = UUID.randomUUID(),

  @Column(nullable = false)
  val projectId: UUID,

  @Column(nullable = false)
  val userId: UUID,

  @Enumerated(EnumType.STRING)
  val role: ProjectUserRole
) {
  companion object {
    fun from(domain: ProjectUser): ProjectUserEntity {
      return ProjectUserEntity(
        projectId = domain.projectId,
        userId = domain.userId,
        role = domain.role
      )
    }
  }

  fun toDomain(): ProjectUser {
    return ProjectUser(
      projectId = projectId,
      userId = userId,
      role = role
    )
  }
}
