FROM openjdk:19
EXPOSE 8080
WORKDIR /usr/src/myapp
ADD backend/target/app.jar app.jar
CMD [ "sh", "-c", "java -jar app.jar"]
#Dspring.data.mongodb.uri=$MONGO_DB_URI this can go to the cmd to add an env variable