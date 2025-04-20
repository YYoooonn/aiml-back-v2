package org.aiml.user.domain.model

import org.aiml.user.domain.command.CreateUserCoreCommand
import org.aiml.user.domain.command.UpdateUserCoreCommand
import java.time.LocalDateTime
import java.util.UUID

data class User(
    val id: UUID = UUID.randomUUID(),
    val username: String,
    val encryptedPassword: String,
    val email: String,
    val role: Role? = Role.USER,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    enum class Role {
        ADMIN, USER
    }

    companion object {
        fun from(command: CreateUserCoreCommand, encrypted: String): User {
            return User(
                id = UUID.randomUUID(),
                username = command.username,
                email = command.email,
                encryptedPassword = encrypted,
            )
        }
    }

    fun update(command: UpdateUserCoreCommand, encrypted: String? = null): User {
        return this.copy(
            username = command.username ?: username,
            encryptedPassword = encrypted ?: encryptedPassword,
            email = command.email ?: email,
        )
    }
}
