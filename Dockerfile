FROM openjdk:17-jdk-slim

EXPOSE 5500

COPY target/MoneyTransferService-1.0-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]