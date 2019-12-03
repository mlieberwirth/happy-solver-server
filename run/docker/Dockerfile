# Base Alpine Linux based image with OpenJDK JRE only
FROM openjdk:8-jre-alpine

# copy application jar
COPY ./happy-solver-server.jar usr/local/happy-solver-server.jar

# specify default command
CMD ["/usr/bin/java", "-jar", "-Dspring.cloud.config.profile=local", "-Dspring.profiles.active=local", "-Xmx512M", "-Xms512M", "usr/local/happy-solver-server.jar"]
