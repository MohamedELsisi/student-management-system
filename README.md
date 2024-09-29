# Student Management System

## Overview

The Student Management System is a REST API application designed to facilitate student registration, course management, and schedule management. It allows users to log in, view available courses, register for courses, cancel registrations, and download their course schedules as PDF files.

## Technical Details

- **Language**: Java 17
- **Framework**: Spring Boot
- **Database**: H2
- **Authentication**: JWT (JSON Web Token)
- **PDF Generation**: iTextPDF for creating downloadable course schedules.

## Features

- User login with JWT authentication.
- View all available courses.
- Register for courses.
- Cancel course registrations.
- Download course schedules in PDF format.

## API Endpoints

### Authentication

- **POST** `/login`
    - Request Body:
      ```json
      {
        "username": "string",
        "password": "string"
      }
      ```
    - Response: JWT token for authorized access.

### Course Management

- **GET** `/api/courses`
    - Description: Retrieve a list of all courses.

- **POST** `/api/courses/register`
    - Request Body:
      ```json
      {
        "userId": "integer",
        "courseId": "integer"
      }
      ```
    - Response: Accepted response upon successful registration.

- **POST** `/api/courses/cancel`
    - Request Body:
      ```json
      {
        "userId": "integer",
        "courseId": "integer"
      }
      ```
    - Response: Accepted response upon successful cancellation.

- **GET** `/api/courses/schedule/download`
    - Query Parameter: `username` (the username of the student)
    - Response: PDF file of the course schedule.

### Swagger
UI is integrated for API documentation. After starting the application, you can access it at `http://localhost:8080/studentManagement/swagger-ui/index.html`.

### Postman Collection 
`https://documenter.getpostman.com/view/25399084/2sAXqzWdJo`

## Configuration

### Properties

Add your configurations in the `application.properties` file:

```properties
jwt.secret=your_jwt_secret
jwt.expiration=300000 # 5 minutes in milliseconds

