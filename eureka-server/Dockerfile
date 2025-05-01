# OpenJDK 17 베이스 이미지 사용
FROM openjdk:17-jdk-alpine

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} eureka-server.jar

# 컨테이너에서 노출할 포트
EXPOSE 8761

# Spring 프로파일을 동적으로 설정할 수 있도록 CMD 명령어 수정
CMD ["java", "-Dspring.profiles.active=${profile:default}", "-jar", "eureka-server.jar"]
