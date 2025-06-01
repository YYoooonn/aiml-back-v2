package org.aiml.user.domain.port.inbound

import org.aiml.user.application.dto.UserProfileDTO
import org.springframework.web.multipart.MultipartFile
import java.util.*

interface UserProfileCommandService {
  fun create(dto: UserProfileDTO): UserProfileDTO
  fun update(dto: UserProfileDTO, file: MultipartFile?): UserProfileDTO

  fun deleteImageByUserId(userId: UUID)

  fun deleteByUserId(userId: UUID)

  fun deleteAll()
}
