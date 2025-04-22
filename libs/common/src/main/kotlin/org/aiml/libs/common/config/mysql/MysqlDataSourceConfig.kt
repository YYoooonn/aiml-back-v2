package org.aiml.libs.common.config.mysql

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.*
import javax.sql.DataSource

object BeanNames {
  const val DATASOURCE = "mysqlDataSource"
  const val JPA_PROPERTIES = "mysqlJpaProperties"
}

@Configuration
@EnableConfigurationProperties(
  MysqlDataSourceProperties::class,
  MysqlJpaProperties::class
)
class MysqlSourceConfig {

  @Bean(name = [BeanNames.DATASOURCE])
  @Primary
  fun mysqlDataSource(props: MysqlDataSourceProperties): DataSource {
    println("✅ mysql url = ${props.url}")
    println("✅ mysql username = ${props.username}")
    println("✅ mysql driver = ${props.driverClassName}")
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
  @Primary
  fun mysqlJpaProperties(props: MysqlJpaProperties): JpaProperties {
    val jpaProps = JpaProperties()
    jpaProps.properties.putAll(props.properties)
    jpaProps.properties["hibernate.hbm2ddl.auto"] = props.hibernate.ddlAuto
    return jpaProps
  }

}

