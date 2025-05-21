// querydsl
apply(plugin = "org.jetbrains.kotlin.plugin.allopen")
apply(plugin = "org.jetbrains.kotlin.plugin.noarg")


dependencies {
  implementation(project(":libs:common"))
  implementation(project(":libs:infra"))
  implementation(project(":modules:project-user"))
  implementation(project(":modules:project"))
  implementation(project(":modules:object3d"))

  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter")
  runtimeOnly("org.postgresql:postgresql")

  // querydsl
  kapt("com.querydsl:querydsl-apt:5.1.0:jakarta")
  implementation("com.querydsl:querydsl-jpa:5.1.0:jakarta")
  implementation("jakarta.annotation:jakarta.annotation-api")
  implementation("jakarta.persistence:jakarta.persistence-api")
}

// kotlin jpa : 아래의 어노테이션 클래스에 no-arg 생성자를 생성
noArg {
  annotation("jakarta.persistence.Entity")
  annotation("jakarta.persistence.MappedSuperclass")
  annotation("jakarta.persistence.Embeddable")
}

// kotlin jpa : 아래의 어노테이션 클래스를 open class 로 자동 설정
allOpen {
  annotation("jakarta.persistence.Entity")
  annotation("jakarta.persistence.MappedSuperclass")
  annotation("jakarta.persistence.Embeddable")
}

kotlin {
  sourceSets.main {
    kotlin.srcDir("build/generated/source/kapt/main")
  }
}
