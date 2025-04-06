dependencies {
  implementation(project(":modules:user"))
  implementation(project(":modules:geometry"))
  implementation(project(":libs:common"))
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter")
}

tasks.getByName("bootJar") {
  enabled = true
}

tasks.getByName("jar") {
  enabled = false
}
