FROM gradle:8.3.0-jdk17-alpine as build

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle build --no-daemon

EXPOSE 8080/tcp

RUN mkdir /app

COPY build/libs/*.jar /app/rock-paper-scissors.jar

CMD ["java", "-jar", "/app/rock-paper-scissors.jar"]