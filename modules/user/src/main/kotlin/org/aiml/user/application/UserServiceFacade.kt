package org.aiml.user.application

import jakarta.transaction.Transactional
import org.aiml.user.application.dto.*
import org.aiml.user.exception.UserNotFoundException
import org.aiml.user.domain.port.inbound.*
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceFacade(
  private val userCoreCommandService: UserCoreCommandService,
  private val userCoreQueryService: UserCoreQueryService,
  private val userProfileCommandService: UserProfileCommandService,
  private val userProfileQueryService: UserProfileQueryService,
) {

  @Transactional
  fun register(dto: UserDTO): UserDTO {
    val core = userCoreCommandService.create(dto.toUserCore())
    val profile = userProfileCommandService.create(dto.toUserProfile())
    return UserDTO.from(core, profile)
  }

  @Transactional
  fun delete(id: UUID) {
    userCoreCommandService.delete(id)
    userProfileCommandService.deleteByUserId(id)
  }

  @Transactional
  fun updateUser(dto: UserDTO): UserDTO {
    val core = userCoreCommandService.update(dto.toUserCore())
    val profile = userProfileCommandService.update(dto.toUserProfile())
    return UserDTO.from(core, profile)
  }

  // query
  fun getUserInfo(userId: UUID): UserDTO {
    val core = userCoreQueryService.findById(userId)
    val profile = userProfileQueryService.findByUserId(userId)
      ?: throw UserNotFoundException("profile not found")
    return UserDTO.from(core, profile)
  }

  // for test
  fun getAllUsers(): List<UserDTO> {
    val cores = userCoreQueryService.findAll()
    val profileMap = userProfileQueryService.findAll().associateBy { it.userId }
    return cores.map { core ->
      val profile = profileMap[core.id] ?: throw UserNotFoundException("profile not found")
      UserDTO.from(core, profile)
    }
  }

}
