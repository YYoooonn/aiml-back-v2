package org.aiml.libs.infra.mysql

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import jakarta.persistence.EntityManagerFactory
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.*
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.orm.jpa.*
import javax.sql.DataSource

object BeanNames {
  const val DATASOURCE = "mysqlDataSource"
  const val JPA_PROPERTIES = "mysqlJpaProperties"
  const val ENTITY_MANAGER_FACTORY = "mysqlEntityManagerFactory"
  const val TRANSACTION_MANAGER = "mysqlTransactionManager"
  const val PERSISTENCE_UNIT = "mysqlPersistenceUnit"
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

  @Bean(name = [BeanNames.ENTITY_MANAGER_FACTORY])
  @Primary
  fun entityManagerFactory(
    builder: EntityManagerFactoryBuilder,
    @Qualifier(BeanNames.DATASOURCE) dataSource: DataSource,
    @Qualifier(BeanNames.JPA_PROPERTIES) jpaProperties: JpaProperties
  ): LocalContainerEntityManagerFactoryBean =
    builder
      .dataSource(dataSource)
      .packages("org.aiml.project", "org.aiml.user", "org.aiml.project_user")
      .persistenceUnit(BeanNames.PERSISTENCE_UNIT)
      .properties(jpaProperties.properties)
      .build()

  @Bean(name = [BeanNames.TRANSACTION_MANAGER])
  @Primary
  fun transactionManager(
    @Qualifier(BeanNames.ENTITY_MANAGER_FACTORY) emf: EntityManagerFactory
  ): PlatformTransactionManager = JpaTransactionManager(emf)

}

