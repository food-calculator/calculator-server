FROM eclipse-temurin:21.0.1_12-jre-alpine
EXPOSE 8080/tcp

RUN mkdir /app
WORKDIR /app

VOLUME /app/databases

COPY build/libs/calc-server-all.jar /app/server.jar

CMD ["java", "-jar", "/app/server.jar"]