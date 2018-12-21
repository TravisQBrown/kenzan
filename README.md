#Project: Kenzan Employee Web Service 
This project is a simple implementation of an employee ReSTful API.
The implementation is written in Java and leverages Spring MVC and JPA libraries.


## Getting Started
git clone <<>>

### Prerequisites
An instance of MySQL should be running. Once running, configure this project to use the MySQL
instance by modifying the file:
* src/main/resources/application.properties
```
spring.datasource.url = jdbc:mysql://<<MySQLHost>>:<<MySQLPort>>/kenzan
spring.datasource.username = <<MySQLUser>>
spring.datasource.password = <<MySQLPassword>>
```

###Start the web server
In the project directory, issue the following command:
MacOS
```
./gradlew build && java -jar build/libs/empsvc-0.0.1.jar
```

Windows
```
.\gradlew build
java -jar build/libs/empsvc-0.0.1.jar
```

###Using the ReSTful services API
#### NOTE: Examples use localhost and port 8080

#####Create a User
```
curl -X POST \
  http://localhost:8080/api/employee \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{ "firstName": "Kenzan", "middleInitial": "Q", "lastName": "Employee", "dateOfBirth": "1970-01-01", "dateOfEmployment": "2010-01-26", "status": "ACTIVE"}'
```
####Get a specific user
```
curl -X GET \
  http://localhost:8080/api/employee/1 \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache'
```
####Get all users
```
curl -X GET \
  http://localhost:8080/api/employees \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache'
```
####Update a user
```
curl -X PUT \
  http://localhost:8080/api/employee \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{"id": 1, "firstName": "Kenzan", "middleInitial": "Q", "lastName": "NewLastName", "dateOfBirth": "1970-01-01", "dateOfEmployment": "2010-01-26", "status": "ACTIVE"}'
```

####Delete a user.
NOTE: The additional Authorization header is required.
```
curl -X DELETE \
  http://localhost:8080/api/employee/ \
  -H 'Authorization: kenzan' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 077580a2-4447-4909-8b93-8d7fe844f415' \
  -H 'cache-control: no-cache' \
  -d '    {
        "id": 1,
        "firstName": "Kenzan",
        "middleInitial": "Q",
        "lastName": "Employee",
        "dateOfBirth": "1970-01-01",
        "dateOfEmployment": "2010-01-26",
        "status": "ACTIVE"
    }'
```


## Built With

* [Spring JPA](https://spring.io/projects/spring-data-jpa) - Database ORM
* [Gradle](https://gradle.org/) - Dependency Management and build tool for Java
* [Spring Boot](https://spring.io/projects/spring-boot) - Web Services 


## Author

* **Travis Brown** - *Code and Documentation* - [Quinnfinity](http://github.com/TravisQBrown
