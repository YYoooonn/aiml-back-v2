package org.aiml.libs.infra.postgres

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.datasource.postgres")
data class PostgresqlDataSourceProperties(
  val url: String,
  val username: String,
  val password: String,
  val driverClassName: String,
  val pool: Pool = Pool(),
  val idle: Int = 1,
) {
  data class Pool(
    val maxSize: Int = 3,
  )
}

@ConfigurationProperties(prefix = "spring.jpa.postgres")
data class PostgresqlJpaProperties(
  val hibernate: Hibernate = Hibernate(),
  val showSql: Boolean = false,
  val properties: Map<String, String> = emptyMap()
) {
  data class Hibernate(
    val ddlAuto: String = "update"
  )
}
