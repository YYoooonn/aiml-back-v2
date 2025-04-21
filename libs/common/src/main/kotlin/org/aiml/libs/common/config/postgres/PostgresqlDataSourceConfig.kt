package org.aiml.libs.common.config.postgres

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.*
import javax.sql.DataSource

object BeanNames {
  const val DATASOURCE = "postgresDataSource"
  const val JPA_PROPERTIES = "postgresJpaProperties"
}

@Configuration
@EnableConfigurationProperties(
  PostgresqlDataSourceProperties::class,
  PostgresqlJpaProperties::class
)
class PostgresqlDataSourceConfig {

  @Bean(name = [BeanNames.DATASOURCE])
  fun postgresqlDataSource(props: PostgresqlDataSourceProperties): DataSource {
    println("✅ postgres url = ${props.url}")
    println("✅ postgres username = ${props.username}")
    println("✅ postgres driver = ${props.driverClassName}")
    val config = HikariConfig().apply {
      jdbcUrl = props.url
      username = props.username
      password = props.password
      driverClassName = props.driverClassName
      maximumPoolSize = props.pool.maxSize
    }
    return HikariDataSource(config)
  }

  @Bean(name = [BeanNames.JPA_PROPERTIES])
  fun postgresqlJpaProperties(props: PostgresqlJpaProperties): JpaProperties {
    val jpaProps = JpaProperties()
    jpaProps.properties.putAll(props.properties)
    jpaProps.properties["hibernate.hbm2ddl.auto"] = props.hibernate.ddlAuto
    return jpaProps
  }
}
