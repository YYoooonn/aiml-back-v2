// plugins {
//    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
// }

rootProject.name = "aiml-back-v2"

include(
  "apps:api",
  "apps:batch",
  "libs:common",
  "modules:user",
  "modules:project",
  "modules:geometry",
  "modules:project-user"
)

pluginManagement {
  val kotlinVersion: String by settings
  val springBootVersion: String by settings
  val springDependencyManagementVersion: String by settings

  resolutionStrategy {
    eachPlugin {
      when (requested.id.id) {
        "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
        "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
        "org.jetbrains.kotlin.plugin.jpa" -> useVersion(kotlinVersion)
        "org.springframework.boot" -> useVersion(springBootVersion)
        "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion)
      }
    }
  }
}
plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
