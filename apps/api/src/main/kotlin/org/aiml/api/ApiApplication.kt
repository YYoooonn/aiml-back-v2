package org.aiml.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(
  scanBasePackages = ["org.aiml"],
  exclude = [
    DataSourceAutoConfiguration::class,
  ]
)
class ApiApplication

fun main(args: Array<String>) {
  runApplication<ApiApplication>(*args)
}
