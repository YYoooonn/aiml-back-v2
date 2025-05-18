package org.aiml.object3d.group.infra.persistence.repository

import org.aiml.object3d.group.infra.persistence.entity.GroupEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface GroupRepository : JpaRepository<GroupEntity, UUID>
