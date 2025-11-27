# Student API - Spring Boot REST API

A Spring Boot REST API for managing students with CRUD operations, integrated with Angular frontend.

## Requirements

- Java 17+
- Maven 3.6+
- Spring Boot 3.3.0
- (Optional) PostgreSQL 16+

## Project Structure

```
student-api/
├── src/main/java/com/example/studentapi
│   ├── StudentApiApplication.java
│   ├── entity/Student.java
│   ├── repository/StudentRepository.java
│   ├── controller/StudentController.java
│   └── exception/GlobalExceptionHandler.java
└── src/main/resources/application.properties
```

## Features

- RESTful API endpoints for CRUD operations
- H2 in-memory database (with console access)
- Optional PostgreSQL support
- Age validation (students must be 20+ years old)
- Input validation using Jakarta Bean Validation
- CORS enabled for Angular frontend (localhost:4200)
- Global exception handling

## Running the Application

### Using Maven

```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

## API Endpoints

- `GET /api/students` - Get all students
- `GET /api/students/{id}` - Get student by ID
- `POST /api/students` - Create a new student
- `PUT /api/students/{id}` - Update a student
- `DELETE /api/students/{id}` - Delete a student

### Example POST Request

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "birthDate": "2000-01-15"
}
```

## H2 Database Console

Access the H2 console at: `http://localhost:8080/h2`

- JDBC URL: `jdbc:h2:mem:studentsdb`
- Username: `sa`
- Password: (leave empty)

## Switching to PostgreSQL

1. Install PostgreSQL and create database:
```sql
CREATE DATABASE studentsdb;
CREATE USER studentuser WITH ENCRYPTED PASSWORD 'studentpass';
GRANT ALL PRIVILEGES ON DATABASE studentsdb TO studentuser;
```

2. Update `src/main/resources/application.properties`:
   - Comment out H2 configuration
   - Uncomment PostgreSQL configuration

3. Restart the application

## Angular Frontend Integration

Update your Angular service to use:
```typescript
private apiUrl = 'http://localhost:8080/api/students';
```

## Validation Rules

- First name: Required, minimum 2 characters
- Last name: Required, minimum 2 characters
- Birth date: Required
- Age: Must be at least 20 years old

## Testing

You can test the API using:
- Postman
- cURL
- Angular frontend
- H2 Console for database inspection

