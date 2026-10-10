#build
FROM maven:3.9-amazoncorretto-25-al2023 AS build
WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests


#run
FROM amazoncorretto:25.0.2
WORKDIR /app

COPY --from=build /build/target/*.jar ./app.jar

EXPOSE 8080
EXPOSE 9090

ENV SPRING_PROFILES_ACTIVE=production
ENV TZ=America/Sao_Paulo

ENTRYPOINT ["java", "-jar", "app.jar"]