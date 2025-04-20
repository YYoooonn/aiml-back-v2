package org.aiml.user.domain.service.core

import org.aiml.user.domain.command.CreateUserCoreCommand
import org.aiml.user.domain.command.UpdateUserCoreCommand
import org.aiml.user.infra.security.CustomUserPrincipal
import org.aiml.user.domain.exception.*
import org.aiml.user.domain.port.inbound.UserCoreUseCase
import org.aiml.user.domain.model.User
import org.aiml.user.domain.port.outbound.UserCorePersistencePort
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

// 복잡해지면 user/service 단위 쪼개기

@Service
class UserCoreService(
  private val userCorePersistencePort: UserCorePersistencePort,
  private val passwordEncoder: PasswordEncoder,
) : UserCoreUseCase {

  override fun getUser(principal: CustomUserPrincipal): User {
    return userCorePersistencePort.findById(principal.userId)
      ?: throw UserNotFoundException(principal.username)
  }

  override fun createUser(command: CreateUserCoreCommand): User {
    assertUserDoesNotExist(command.username)
    val encryptedPassword = passwordEncoder.encode(command.password)
    val newUser = User.from(command, encryptedPassword)
    return userCorePersistencePort.save(newUser) ?: throw Exception("error while creating user")
  }

  override fun updateUser(principal: CustomUserPrincipal, command: UpdateUserCoreCommand): User {
    val old = userCorePersistencePort.findById(principal.userId)
      ?: throw UserNotFoundException(principal.username)

    val encryptedPassword = command.password?.let { passwordEncoder.encode(it) }
    val new = old.update(command, encryptedPassword)
    return userCorePersistencePort.save(new)
      ?: throw RuntimeException("error while updating user")
  }

  override fun deleteUser(principal: CustomUserPrincipal) {
    checkUserExists(principal.userId)
    return userCorePersistencePort.delete(principal.userId)
  }

  override fun getAllUsers(): Iterable<User> {
    return userCorePersistencePort.findAll()
  }

  override fun getUserByUsername(username: String): User {
    return userCorePersistencePort.findByUsername(username)
      ?: throw UserNotFoundException(username)
  }

  override fun getUserById(id: UUID): User {
    return userCorePersistencePort.findById(id)
      ?: throw UserNotFoundException(id.toString())
  }

  // 내부 로직 체크용

  fun checkUserExists(id: UUID): Boolean {
    return userCorePersistencePort.existsById(id)
  }

  fun checkUserExists(username: String): Boolean {
    return userCorePersistencePort.existsByUsername(username)
  }

  fun assertUserDoesNotExist(username: String) {
    if (checkUserExists(username)) {
      throw UserAlreadyExistsException("User $username already exists")
    }
  }
}
