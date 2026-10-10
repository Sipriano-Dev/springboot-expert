#build
FROM maven:3.9-amazoncorretto-25-al2023 as build
WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests


#run
FROM amazoncorretto:25.0.2
WORKDIR /app

COPY --from=build ./build/target/*.jar ./app.jar

EXPOSE 8080
EXPOSE 9090

ENV DATASOURCE_URL='
ENV DATASOURCE_USERNAME=''
ENV DATASOURCE_PASSWORD=''
ENV GOOGLE_CLIENT_ID='client_id'
ENV GOOGLE_CLIENT_SECRET='client_secret'
ENV SPRING PROFILES_ACTIVE='production'
ENV TZ='America/Sao_Paulo'

ENTRYPOINT exec java -jar libraryapi.jar