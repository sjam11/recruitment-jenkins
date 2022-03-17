# IV1201
This web-application is a part of the course IV1201 Architecture and Design of Global Applications at KTH, Stockholm, Sweden.
The application is a recruitment application for an amusement park. For more information, see this projects **Wiki** page.


## Installation

After you have cloned this repository, use the following commands to install the application.

```bash
mvn clean install
```

```bash
mvn spring-boot:run
```

## Database configuration
The database is deployed on **Heroku**.
To configurate your database you need to set the environment variables in Heroku.
```
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
```
