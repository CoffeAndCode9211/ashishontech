FROM openjdk:8-jdk-alpine
LABEL maintainer="sample@gmail.com"
RUN mkdir -p /usr/src/myapp
COPY target/demo.jar /usr/src/myapp
WORKDIR /usr/src/myapp
EXPOSE 8080
CMD ["java", "-jar", "demo.jar"]