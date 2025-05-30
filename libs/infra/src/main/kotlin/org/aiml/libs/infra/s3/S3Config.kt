package org.aiml.libs.infra.s3

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.presigner.S3Presigner
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class S3Config (
  @Value("\${cloud.aws.region.static}") private val region: String,
  @Value("\${cloud.aws.credentials.accessKey}") private val accessKey: String,
  @Value("\${cloud.aws.credentials.secret-key}") private val secretKey: String
){

  @Bean
  fun s3Client(): S3Client {
    val builder = S3Client.builder()
      .region(Region.of(region))
      .credentialsProvider(
        StaticCredentialsProvider.create(
          AwsBasicCredentials.create(accessKey, secretKey)
        )
      )

    return builder.build()
  }

  @Bean
  fun s3Presigner(): S3Presigner {
    val builder = S3Presigner.builder()
      .region(Region.of(region))
      .credentialsProvider(
        StaticCredentialsProvider.create(
          AwsBasicCredentials.create(accessKey, secretKey)
        )
      )

    return builder.build()
  }
}
