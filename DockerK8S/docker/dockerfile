FROM alpine:3.14

RUN  apk update \
       && apk upgrade \
       && apk add --update openjdk11 tzdata curl unzip bash \
       && rm -rf /var/cache/apk/*

COPY ./target/*.jar /app/application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/application.jar"]