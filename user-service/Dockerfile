# ========== GIAI ĐOẠN 1: BUILD ==========
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy toàn bộ dự án vào container
COPY . .

# Build jar file (bỏ test để build nhanh)
RUN mvn clean package -DskipTests

# ========== GIAI ĐOẠN 2: RUN ==========
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy jar từ giai đoạn build sang
COPY --from=builder /app/target/*.jar app.jar

# Expose port
EXPOSE 8081

# Command chạy app
ENTRYPOINT ["java", "-jar", "app.jar"]
