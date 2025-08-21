FROM 10.10.63.97:8083/git-alpine:2.49.1 AS git-clone
ARG BEAPP_GIT_BRANCH="demo/spring-boot-helloworld"
ARG BEAPP_GIT_URL="http://10.10.64.98/chenweiguang/docker-guide.git"

WORKDIR /artifacts
RUN git clone -b ${BEAPP_GIT_BRANCH} ${BEAPP_GIT_URL} /artifacts

FROM 10.10.63.97:8083/ieds-maven:3.9-amazoncorretto-17-alpine AS maven-build

ARG ENTRANCE_MODULE="spring-boot-helloworld"

ENV ARTIFACTS_DIR=/artifacts \
    APP_DIR=/app

COPY --from=git-clone /artifacts /artifacts
WORKDIR /artifacts/${ENTRANCE_MODULE}

RUN mvn package -q -DskipTests
RUN mkdir mkdir /app && mv ./target/*.jar /app/app.jar

FROM 10.10.63.97:8083/amazoncorretto:17.0.16-al2023-headless

WORKDIR /app
COPY --from=maven-build /app /app

EXPOSE 8484

CMD ["java", "-jar", "app.jar"]