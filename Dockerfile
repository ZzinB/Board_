# Dockerfile
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
#ARG JAR_FILE=build/libs/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]
#


FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]