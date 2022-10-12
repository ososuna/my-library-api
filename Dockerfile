FROM openjdk:11
ENV datasource_url jdbc:mysql://forclass.cwbvgfk36dvj.us-east-1.rds.amazonaws.com/mylibrary?useSSL=false
COPY ./target/spring-book-0.1.0.jar app.jar
EXPOSE 3001
ENTRYPOINT [ "java", "-jar", "app.jar" ]