package org.aiml.libs.infra.postgres

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.*
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

object BeanNames {
  const val DATASOURCE = "postgresDataSource"
  const val JPA_PROPERTIES = "postgresJpaProperties"
  const val ENTITY_MANAGER_FACTORY = "postgresEntityManagerFactory"
  const val TRANSACTION_MANAGER = "postgresTransactionManager"
  const val PERSISTENCE_UNIT = "postgresPersistenceUnit"
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

  @Bean(name = [BeanNames.ENTITY_MANAGER_FACTORY])
  fun entityManagerFactory(
    builder: EntityManagerFactoryBuilder,
    @Qualifier(BeanNames.DATASOURCE) dataSource: DataSource,
    @Qualifier(BeanNames.JPA_PROPERTIES) jpaProperties: JpaProperties
  ): LocalContainerEntityManagerFactoryBean =
    builder
      .dataSource(dataSource)
      .packages("org.aiml.object3d", "org.aiml.scene")
      .persistenceUnit(BeanNames.PERSISTENCE_UNIT)
      .properties(jpaProperties.properties)
      .build()

  @Bean(name = [BeanNames.TRANSACTION_MANAGER])
  fun transactionManager(
    @Qualifier(BeanNames.ENTITY_MANAGER_FACTORY) emf: EntityManagerFactory
  ): PlatformTransactionManager = JpaTransactionManager(emf)
}
