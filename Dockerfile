FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
 
# docker build
# sudo docker build -t alfonsojari/api .

# docker run
# sudo docker run --name SpringAPI-CRUD -p 5025:8080 alfonsojari/api