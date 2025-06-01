package org.aiml.user.domain.port.outbound

import org.springframework.web.multipart.MultipartFile
import java.util.*

interface UserFilePort {
  fun uploadProfilePicture(userId: UUID, file: MultipartFile): Result<String>
  fun deleteProfilePicture(filePath: String): Result<Unit>
}
