FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
RUN apk update && apk upgrade && apk add curl
ARG SPRING_HOME_DIR=/home/spring
RUN mkdir -p $SPRING_HOME_DIR/app && chown -R spring:spring $SPRING_HOME_DIR/app && mkdir -p $SPRING_HOME_DIR/app/log && chown -R spring:spring $SPRING_HOME_DIR/app/log
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} $SPRING_HOME_DIR/app/app.jar
USER spring:spring
ENV SPRING_HOME_DIR_ENV=$SPRING_HOME_DIR
ENTRYPOINT ["java","-jar","/home/spring/app/app.jar"]
#ENTRYPOINT ["tail","-f","/dev/null"]  # this line is just for testing, keep container running and check inside directories and files