# 1. JDK 기반 이미지 선택
FROM eclipse-temurin:17-jdk-alpine

# 2. 컨테이너 내 작업 디렉토리
WORKDIR /app

# 3. 빌드 결과물 복사
COPY build/libs/*.jar app.jar

# 4. 로그 디렉토리 생성
RUN mkdir -p /app/logs

# 5. 환경변수로 Spring 프로파일 설정 (prd 활성화 가능)
ENV SPRING_PROFILES_ACTIVE=prd

# 6. 컨테이너 실행 시 Java 애플리케이션 실행
CMD ["java", "-jar", "app.jar"]
