package org.aiml.user.domain.service.profile

import org.aiml.user.domain.command.UpdateUserProfileCommand
import org.aiml.user.domain.model.User
import org.aiml.user.domain.port.outbound.UserCorePersistencePort
import org.springframework.stereotype.Service

@Service
class UpdateUserProfileService(
    private val userCorePersistencePort: UserCorePersistencePort
) {
    fun update(command: UpdateUserProfileCommand): User? {
        TODO()
    }
}
