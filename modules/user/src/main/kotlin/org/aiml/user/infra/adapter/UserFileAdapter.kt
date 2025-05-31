package org.aiml.user.infra.adapter

import org.aiml.libs.common.file.FileStorage
import org.aiml.user.domain.port.outbound.UserFilePort
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Component
class UserFileAdapter(
  private val fileStorage: FileStorage, // Assuming you have a FileStorage interface for file operations
) : UserFilePort {

  override fun uploadProfilePicture(userId: UUID, file: MultipartFile): Result<String> {

    return try {
      // Define the path where the profile picture will be stored
      val path = "user/profile_pictures/${UUID.randomUUID()}"

      // Upload the file using the file storage service
      val filePath = fileStorage.uploadFile(file, path)

      // Return the URL of the uploaded profile picture
      Result.success(filePath)
    } catch (e: Exception) {
      // Handle any exceptions that occur during file upload
      Result.failure(e)
    }
  }
}
