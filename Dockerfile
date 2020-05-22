FROM openjdk:8-jre-slim
COPY ./target/backend-0.0.1-SNAPSHOT.war /usr/src/app/
WORKDIR /usr/src/app
EXPOSE 8080
CMD ["java", "-jar", "backend-0.0.1-SNAPSHOT.war"]
