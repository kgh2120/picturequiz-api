FROM openjdk:oraclelinux8
VOLUME /tmp
COPY build/libs/PictureQuiz-API-0.0.1-SNAPSHOT.jar api.jar
ENTRYPOINT ["java","-jar","api.jar"]