dependencies {
  implementation(project(":modules:user"))
  implementation(project(":modules:project"))
  implementation(project(":modules:project-user"))
  implementation(project(":modules:geometry"))
  implementation(project(":libs:common"))
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter")

  implementation("org.springframework.boot:spring-boot-starter-security")

  // JWT (jjwt API + impl + jackson for parsing)
  implementation("io.jsonwebtoken:jjwt-api:0.11.5")
  runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
  runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

}

tasks.getByName("bootJar") {
  enabled = true
}

tasks.getByName("jar") {
  enabled = false
}
