FROM 10.10.63.97:8083/git-alpine:2.49.1 AS git-clone

ARG BEAPP_GIT_URL
ARG BEAPP_GIT_BRANCH

WORKDIR /artifacts
RUN git clone -b ${BEAPP_GIT_BRANCH} ${BEAPP_GIT_URL} /artifacts

FROM 10.10.63.97:8083/maven:3.9-amazoncorretto-17-alpine AS maven-build

ARG ENTRANCE_MODULE

COPY --from=git-clone /artifacts /artifacts
WORKDIR /artifacts/${ENTRANCE_MODULE}

RUN mvn package -DskipTests -q
RUN mkdir mkdir /app && mv ./target/*.jar /app/app.jar

FROM 10.10.63.97:8083/amazoncorretto:17.0.16-al2023-headless

WORKDIR /app
COPY --from=maven-build /app /app

EXPOSE 8383

CMD ["java", "-jar", "app.jar"]