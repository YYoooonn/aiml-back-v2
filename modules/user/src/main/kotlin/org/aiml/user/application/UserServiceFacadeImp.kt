package org.aiml.user.application

import jakarta.transaction.Transactional
import org.aiml.user.infra.security.CustomUserPrincipal
import org.aiml.user.domain.model.User
import org.aiml.user.domain.port.inbound.*
import org.springframework.stereotype.Service
import java.util.UUID

@Transactional
@Service
class UserServiceFacadeImp(
  private val userCoreUseCase: UserCoreUseCase,
  private val userProfileUseCase: UserProfileUseCase
) : UserServiceFacade {
  override fun register(command: RegisterCommand): Result<UserDTO> {
    return runCatching {
      val user = userCoreUseCase.createUser(command.toCoreCommand())
      val profile = userProfileUseCase.createUserProfile(command.toProfileCommand(), user)
      UserDTO.from(user, profile)
    }
  }

  override fun getUserCore(username: String): Result<User> {
    return runCatching {
      userCoreUseCase.getUserByUsername(username)
    }
  }

  override fun getUserCore(userId: UUID): Result<User> {
    return runCatching {
      userCoreUseCase.getUserById(userId)
    }
  }

  override fun getProfile(username: String): Result<UserDTO> {
    return runCatching {
      val user = userCoreUseCase.getUserByUsername(username)
      val profile = userProfileUseCase.getUserProfile(username)
      UserDTO.from(user, profile)
    }
  }

  override fun delete(principal: CustomUserPrincipal): Result<Unit> {
    return runCatching {
      userCoreUseCase.deleteUser(principal)
      userProfileUseCase.deleteUserProfile(principal)
    }
  }

  override fun getUser(principal: CustomUserPrincipal): Result<UserDTO> {
    return runCatching {
      val user = userCoreUseCase.getUser(principal)
      val profile = userProfileUseCase.getUserProfile(principal)
      UserDTO.from(user, profile)
    }
  }

  override fun getProfile(principal: CustomUserPrincipal): Result<UserDTO> {
    return runCatching {
      val user = userCoreUseCase.getUser(principal)
      val profile = userProfileUseCase.getUserProfile(principal)
      UserDTO.from(user, profile)
    }
  }

  override fun updateUser(principal: CustomUserPrincipal, command: UpdateCommand): Result<UserDTO> {
    return runCatching {
      val user = userCoreUseCase.updateUser(principal, command.toCoreCommand())
      val profile = userProfileUseCase.getUserProfile(principal)
      UserDTO.from(user, profile)
    }
  }

  override fun updateUserProfile(principal: CustomUserPrincipal, command: UpdateCommand): Result<UserDTO> {
    return runCatching {
      val user = userCoreUseCase.getUser(principal)
      val profile = userProfileUseCase.updateUserProfile(principal, command.toProfileCommand(), user)
      UserDTO.from(user, profile)
    }
  }

  override fun getUsers(): Result<List<User>> {
    TODO("Not yet implemented")
  }

  override fun getProfiles(): Result<List<UserDTO>> {
    TODO("Not yet implemented")
  }

}
