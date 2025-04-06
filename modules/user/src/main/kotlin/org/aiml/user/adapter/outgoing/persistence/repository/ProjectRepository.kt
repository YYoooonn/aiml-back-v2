package org.aiml.user.adapter.outgoing.persistence.repository

import org.aiml.user.adapter.outgoing.persistence.entity.ProjectEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<ProjectEntity, Long> {
}
