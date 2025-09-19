FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/commerce.admin.service-0.0.1-SNAPSHOT.jar /app/commerce-admin-service.jar

EXPOSE 8080

CMD ["java","-jar","commerce-admin-service.jar"]