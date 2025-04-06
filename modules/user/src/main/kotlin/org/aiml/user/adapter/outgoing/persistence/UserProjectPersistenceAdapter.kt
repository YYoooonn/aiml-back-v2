package org.aiml.user.adapter.outgoing.persistence

import org.aiml.user.adapter.outgoing.persistence.entity.UserProjectId
import org.aiml.user.adapter.outgoing.persistence.mapper.UserProjectMapper
import org.aiml.user.adapter.outgoing.persistence.repository.ProjectRepository
import org.aiml.user.adapter.outgoing.persistence.repository.UserProjectRepository
import org.aiml.user.adapter.outgoing.persistence.repository.UserRepository
import org.aiml.user.core.application.port.outgoing.UserProjectPersistencePort
import org.aiml.user.core.domain.model.UserProject
import org.springframework.stereotype.Component

@Component
class UserProjectPersistenceAdapter(
  private val userProjectRepository: UserProjectRepository,
  private val userRepository: UserRepository,
  private val projectRepository: ProjectRepository,
  private val mapper: UserProjectMapper
) : UserProjectPersistencePort {

  override fun findByProjectId(projectId: Long): List<UserProject> {
    return userProjectRepository.findAllByProjectId(projectId).map { mapper.toDomain(it) }
  }

  override fun findByUserId(userId: Long): List<UserProject> {
    return userProjectRepository.findAllByUserId(userId).map { mapper.toDomain(it) }
  }

  override fun save(userProject: UserProject, userId: Long, projectId: Long): UserProject {
    val user = userRepository.getReferenceById(userId)
    val project = projectRepository.getReferenceById(projectId)
    val entity = mapper.toEntity(userProject, user, project)
    return mapper.toDomain(userProjectRepository.save(entity))

  }

  override fun delete(userId: Long, projectId: Long) {
    val id = UserProjectId(userId, projectId)
    userProjectRepository.deleteById(id)
  }
}
