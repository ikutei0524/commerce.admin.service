FROM maven:3.8-openjdk-17 AS builder

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /app/target/commerce.admin.service-0.0.1-SNAPSHOT.jar /app/commerce-admin-service.jar

EXPOSE 8080

CMD ["java","-jar","commerce-admin-service.jar"]