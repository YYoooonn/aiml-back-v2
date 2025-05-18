package org.aiml.project.infra.persistence.repository

import org.aiml.project.infra.persistence.ProjectEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProjectRepository : JpaRepository<ProjectEntity, UUID>
