FROM gradle:8.7.0-jdk21-alpine as builder

WORKDIR /app

COPY . /app/.

RUN chmod +x gradle

RUN gradle clean build -x test

FROM openjdk:21

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

COPY ./src/main/resources/firebase/start-8fcf1-firebase-adminsdk-cxqyu-cb4a3970d1.json /app/firebase/firebase.json

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
