## Dockerfile
#
## jdk17 Image Start
#FROM openjdk:17
#### 인자 설정 - JAR_File
###ARG JAR_FILE=build/libs/*.jar
#### jar 파일 복제
###COPY ${JAR_FILE} app.jar
##COPY build/libs/*.jar app.jar
### 실행 명령어
##ENTRYPOINT ["java", "-jar", "/app.jar"]
#ARG JAR_FILE=builds/libs/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]
#

FROM bellsoft/liberica-openjdk-alpine:17
# or
# FROM openjdk:8-jdk-alpine
# FROM openjdk:11-jdk-alpine

CMD ["./gradlew", "clean", "build"]
# or Maven
# CMD ["./mvnw", "clean", "package"]

VOLUME /tmp

ARG JAR_FILE=build/libs/*.jar
# or Maven
# ARG JAR_FILE_PATH=target/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]