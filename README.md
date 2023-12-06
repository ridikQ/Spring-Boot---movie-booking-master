# Spring-Boot---movie-booking-master
This is a simple movie booking project made with java and spring boot framework.
Project made with intuit to learn java spring boot framework
## Database
It uses a MySQL in memory database (for now), can be changed easily in the application.yml for any other database.
## Running the app
You need [Java](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html) and [Maven](https://maven.apache.org/download.cgi) installed

1.Build Spring Boot Project with Maven

    mvn package

2.Run Spring Boot app using Maven:

   `mvn spring-boot:run` 
   
3.Go to http://localhost:8080/swagger-ui.html

## Features
- User registration and login with JWT authentication
- Password encryption using BCrypt
- Role-based authorization with Spring Security
- Customized access denied handling
- Logout mechanism
- Refresh token

## Tech Stack used in the project:
- Java 17
- SpringBoot
- Spring MVC
- Spring Data JPA
- Spring Security
- MySQL database
- Lombok
- Open API
- Flyway
- OAuth2
