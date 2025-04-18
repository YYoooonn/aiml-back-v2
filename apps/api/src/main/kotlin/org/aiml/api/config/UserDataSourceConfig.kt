package org.aiml.api.config

import com.zaxxer.hikari.HikariDataSource
import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.*
import org.springframework.orm.jpa.*
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = ["org.aiml.user.infra.persistence.repository"],
  entityManagerFactoryRef = "userEntityManagerFactory",
  transactionManagerRef = "userTransactionManager"
)
class UserDataSourceConfig {

  @Primary
  @Bean(name = ["mysqlDataSourceProperties"])
  @ConfigurationProperties(prefix = "spring.datasource")
  fun mysqlDataSourceProperties(): DataSourceProperties {
    return DataSourceProperties()
  }


//  @Primary
//  @Bean(name = ["userDataSource"])
//  @ConfigurationProperties(prefix = "mysql.datasource.configuration")
//  fun userDataSource(@Qualifier("mysqlDataSourceProperties") prop: DataSourceProperties): DataSource {
//    return prop
//      .initializeDataSourceBuilder()
//      .type(HikariDataSource::class.java)
//      .build()
//  }

  @Primary
  @Bean(name = ["userDataSource"])
  @ConfigurationProperties(prefix = "spring.datasource.configuration")
  fun userDataSource(
    @Qualifier("mysqlDataSourceProperties") prop: DataSourceProperties
  ): DataSource {
    return prop
      .initializeDataSourceBuilder()
      .type(HikariDataSource::class.java)
      .build()
  }

  @Primary
  @Bean(name = ["mysqlJpaProperties"])
  @ConfigurationProperties(prefix = "spring.jpa")
  fun mysqlJpaProperties(): JpaProperties = JpaProperties()

  @Primary
  @Bean(name = ["userEntityManagerFactory"])
  fun userEntityManagerFactory(
    builder: EntityManagerFactoryBuilder,
    @Qualifier("userDataSource") dataSource: DataSource,
    @Qualifier("mysqlJpaProperties") jpaProperties: JpaProperties
  ): LocalContainerEntityManagerFactoryBean =
    builder
      .dataSource(dataSource)
      .packages("org.aiml.user.infra.entity")
      .persistenceUnit("user")
      .properties(jpaProperties.properties)
      .build()

  @Primary
  @Bean(name = ["userTransactionManager"])
  fun userTransactionManager(
    @Qualifier("userEntityManagerFactory") emf: EntityManagerFactory
  ): PlatformTransactionManager =
    JpaTransactionManager(emf)
}
