package org.aiml.user.application.command

import org.aiml.user.application.dto.UserCoreDTO
import org.aiml.user.domain.port.inbound.UserCoreCommandService
import org.aiml.user.domain.port.outbound.UserCorePersistencePort
import org.aiml.user.exception.EmailAlreadyExistsException
import org.aiml.user.exception.UserAlreadyExistsException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserCoreCommandServiceImpl(
  private val userCorePersistencePort: UserCorePersistencePort,
  private val passwordEncoder: PasswordEncoder
) : UserCoreCommandService {

  override fun create(user: UserCoreDTO): UserCoreDTO {
    checkDuplicates(user)
    return save(user)
  }

  override fun delete(id: UUID) {
    return userCorePersistencePort.delete(id).getOrThrow()
  }

  override fun update(user: UserCoreDTO): UserCoreDTO {
    return save(user)
  }

  private fun checkDuplicates(dto: UserCoreDTO) {
    if (userCorePersistencePort.existsByUsername(dto.username)) {
      throw UserAlreadyExistsException("User ${dto.username} already exists")
    }
    if (userCorePersistencePort.existsByEmail(dto.email)) {
      throw EmailAlreadyExistsException("User ${dto.email} already exists")
    }
  }

  private fun save(user: UserCoreDTO): UserCoreDTO {
    val encryptPassword = passwordEncoder.encode(user.password)
    val saved = userCorePersistencePort.save(user.toDomain(encryptPassword)).getOrThrow()
    return UserCoreDTO.from(saved)
  }


  override fun deleteAll() {
    return userCorePersistencePort.deleteAll().getOrThrow()
  }

}
