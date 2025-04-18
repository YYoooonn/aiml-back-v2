package org.aiml.user.domain.service.user

import org.aiml.user.domain.model.User
import org.aiml.user.domain.port.output.UserPersistencePort
import org.aiml.user.domain.service.UserDomainFacade
import org.springframework.stereotype.Service

@Service
class UpdateUserService(
  private val userPersistencePort: UserPersistencePort,
  private val userDomainFacade: UserDomainFacade
) {
  fun update(command: UpdateUserCommand): User {
    TODO()
  }
}
