package org.aiml.libs.common.file.s3

import org.aiml.libs.common.file.FileStorage
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import software.amazon.awssdk.services.s3.model.GetObjectRequest
import software.amazon.awssdk.services.s3.presigner.S3Presigner
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest
import software.amazon.awssdk.core.sync.RequestBody
import java.time.Duration

@Component
class S3FileStorage(
  private val s3Client: S3Client,
  private val s3Presigner: S3Presigner,
  @Value("\${cloud.aws.s3.bucket}") private val bucketName: String,
) : FileStorage {

  override fun uploadFile(file: MultipartFile, path: String): String {

    val putObjectRequest = PutObjectRequest.builder()
      .bucket(bucketName)
      .key(path)
      .contentType(file.contentType)
      .build()

    s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.inputStream, file.size))

    return putObjectRequest.key()
  }

  override fun getUrl(path: String?): String {
    val getObjectRequest = GetObjectRequest.builder()
      .bucket(bucketName)
      .key(path)
      .build()

    val presignRequest = GetObjectPresignRequest.builder()
      .getObjectRequest(getObjectRequest)
      .signatureDuration(Duration.ofMinutes(15))
      .build()

    val presignedUrl = s3Presigner.presignGetObject(presignRequest)

    return presignedUrl.url().toString()
  }
}
