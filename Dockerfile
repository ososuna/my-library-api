FROM openjdk:11
ENV datasource_url jdbc:mysql://host.docker.internal:3306/spring-book?useSSL=false
COPY ./target/spring-book-0.1.0.jar app.jar
EXPOSE 3001
ENTRYPOINT [ "java", "-jar", "app.jar" ]