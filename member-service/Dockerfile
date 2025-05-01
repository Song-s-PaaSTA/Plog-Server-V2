# OpenJDK 17 베이스 이미지 사용
FROM openjdk:17-jdk-alpine

# JAR 파일을 복사
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} member-service.jar

# 컨테이너에서 노출할 포트
EXPOSE 8001

# 환경 변수 설정
ENV SPRING_PROFILES_ACTIVE=k8s

# Spring 프로파일을 동적으로 설정할 수 있도록 명령어 수정
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "member-service.jar"]

