FROM openjdk:oraclelinux8
VOLUME /tmp
COPY build/libs/PictureQuiz-API-0.0.1-SNAPSHOT.jar api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=prod" ,"api.jar"]