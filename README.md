# Student Management API

A secure RESTful API built with Spring Boot for managing student records, featuring JWT authentication and role-based authorization.

## Features

- CRUD operations for student records
- H2 in-memory database
- JWT-based authentication
- Role-based access control (ADMIN/USER)
- Input validation and error handling

## Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher
- Postman (for API testing)

### For the Database Configuration
The application uses an H2 in-memory database by default.  <br>
To access the H2 console:

Application must be running <br>

Visit: http://localhost:8080/h2-console (you can adjust the port)

| JDBC URL  | jdbc:h2:mem:studentdb |
|-----------|-----------------------|
| Username  |           sa          |
| Password  |                       |

### For testing use POSTMAN for sending requests 

1. POST http://localhost:8080/api/auth/register  <br>
   send requreied data for registering new user  <br>{ "firstname": "admin", "lastname": "1", "role": "ADMIN", "email": "admin@example.com", "password": "admin123" }   
   
2. POST http://localhost:8080/api/auth/authenticate  <br>
   send the credintials  <br>{ "email": "admin@example.com", "password": "admin123" } <br>
   then use the generated token for Authorization

3. POST http://localhost:8080/api/students  <br>
    send required data for registering a new student <br> { "firstName": "first", "lastName": "student", "email": "A@example.com", "dateOfBirth": "2007-12-09" }

 #### Student endpoints  <br>
 

|    Endpoint           | Method |     Description      | Required Role  |
|-----------------------|--------|----------------------|----------------|
| /api/students         |  POST  | Create new student   |     ADMIN      |
| /api/students         |  GET   | Get all students     |  ADMIN, USER   |
| /api/students/{id}    |  GET   | Get a student by ID  |  ADMIN, USER   |
| /api/students/{id}    |  PUT   | Update a student     |     ADMIN      |
| /api/students/{id}    | DELETE | Delete a student     |     ADMIN      |

