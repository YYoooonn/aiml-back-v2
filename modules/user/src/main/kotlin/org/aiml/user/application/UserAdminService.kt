package org.aiml.user.application

import org.aiml.user.application.dto.UserDTO
import org.aiml.user.domain.port.inbound.UserCoreCommandService
import org.aiml.user.domain.port.inbound.UserCoreQueryService
import org.aiml.user.domain.port.inbound.UserProfileCommandService
import org.aiml.user.domain.port.inbound.UserProfileQueryService
import org.aiml.user.exception.UserNotFoundException
import org.springframework.stereotype.Service


@Service
class UserAdminService(
  private val userCoreCommandService: UserCoreCommandService,
  private val userCoreQueryService: UserCoreQueryService,
  private val userProfileCommandService: UserProfileCommandService,
  private val userProfileQueryService: UserProfileQueryService,
) {

  fun getAllUsers(): List<UserDTO> {
    val cores = userCoreQueryService.findAll()
    val profileMap = userProfileQueryService.findAll().associateBy { it.userId }
    return cores.map { core ->
      val profile = profileMap[core.id] ?: throw UserNotFoundException("profile not found")
      UserDTO.from(core, profile)
    }
  }

  fun deleteAllUsers() {
    userProfileCommandService.deleteAll()
    return userCoreCommandService.deleteAll()
  }

}
