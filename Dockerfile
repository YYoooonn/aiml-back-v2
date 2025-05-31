# 1. 빌드 스테이지 : local 에선 주석처리하고 ./gradlew build
FROM gradle:8.4-jdk17 AS build
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test -x ktlintCheck -x ktlintKotlinScriptCheck

# 2. 런타임 스테이지
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# COPY ./apps/api/build/libs/*.jar app.jar
COPY --from=build  /app/apps/api/build/libs/*.jar app.jar
EXPOSE 8080

# JVM 메모리 제한 및 성능 최적화
ENV JAVA_OPTS="-Xms128m -Xmx256m -XX:+UseSerialGC"

# 실행
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

## ---------- Step 1: Build Stage ----------
#FROM gradle:8.12-jdk17 AS builder
#
#WORKDIR /app
#
## 필요한 파일만 복사
#COPY build.gradle.kts settings.gradle.kts ./
#
#COPY modules modules
#COPY apps apps
#COPY libs libs
#COPY config config
#
#COPY .env .env
#
#COPY gradle gradle
#COPY gradlew .
#COPY gradlew.bat .
#COPY gradle.properties .
#
## 빌드 (루트 build → copyApiJar 포함됨)
#RUN ./gradlew build -x test -x ktlintCheck -x ktlintKotlinScriptCheck
#
## ---------- Step 2: Runtime Stage ----------
#FROM openjdk:17-slim AS runtime
#
#WORKDIR /app
#
## 복사된 app.jar만 가져옴
#COPY --from=builder /app/build/libs/app.jar .
#
#ENTRYPOINT ["java", "-jar", "app.jar"]
