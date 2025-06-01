package org.aiml.user.application.command

import org.aiml.user.application.dto.UserProfileDTO
import org.aiml.user.domain.port.inbound.UserProfileCommandService
import org.aiml.user.domain.port.outbound.UserFilePort
import org.aiml.user.domain.port.outbound.UserProfilePersistencePort
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@Service
class UserProfileCommandServiceImpl(
  private val userProfilePersistencePort: UserProfilePersistencePort,
  private val userFilePort: UserFilePort,
) : UserProfileCommandService {

  // create save 차이 현재 없음
  override fun create(dto: UserProfileDTO): UserProfileDTO {
    val profile = userProfilePersistencePort.save(dto.toDomain()).getOrThrow()
    return UserProfileDTO.from(profile)
  }


  override fun update(dto: UserProfileDTO, file: MultipartFile?): UserProfileDTO {
    if (file != null) {
      // If a file is provided, upload the profile picture
      val path = userFilePort.uploadProfilePicture(dto.userId, file).getOrThrow()
      dto.imageUrl = path
    }
    val profile = userProfilePersistencePort.upsert(dto.toDomain()).getOrThrow()
    return UserProfileDTO.from(profile)
  }

  override fun deleteImageByUserId(userId: UUID) {
    // Update the user profile to remove the image URL
    val filePath = userProfilePersistencePort.deleteImageByUserId(userId).getOrThrow()
    // Delete the image file from S3
    userFilePort.deleteProfilePicture(filePath).getOrThrow()
  }

  override fun deleteByUserId(userId: UUID) {
    userProfilePersistencePort.deleteByUserId(userId).getOrThrow()
  }

  override fun deleteAll() {
    userProfilePersistencePort.deleteAll().getOrThrow()
  }
}
