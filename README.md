# Movie Management System Projects Collection

This repository contains a series of Java projects demonstrating the evolution of a Movie Management System through different technologies and architectures. Each project represents a different implementation approach, from simple console applications to web-based solutions using various frameworks.

## Table of Contents

1. [exp_1: JDBC Console Application](#exp_1-jdbc-console-application)
2. [exp_2: Client-Server Architecture](#exp_2-client-server-architecture)
3. [exp_3_final: Servlet-based Web Application](#exp_3_final-servlet-based-web-application)
4. [exp_4: JSP-based Web Application](#exp_4-jsp-based-web-application)
5. [exp_5: JSF Web Application](#exp_5-jsf-web-application)
6. [exp_6: Custom Tag Library](#exp_6-custom-tag-library)
7. [exp_7: Hibernate Experiment](#exp_7-hibernate-experiment)
8. [exp_8: Advanced Hibernate Implementation](#exp_8-advanced-hibernate-implementation)
9. [MovieCRUDApp10: Complete JSF Application](#moviecrudapp10-complete-jsf-application)
10. [MovieManagementSystem: Spring-based Implementation](#moviemanagementsystem-spring-based-implementation)

## exp_1: JDBC Console Application

A simple console-based movie management application using JDBC to connect to a MySQL database.

### Features

- Add new movies to the database
- View all movies in the database
- Modify movie details
- Delete movies from the database

### Technologies

- Java
- JDBC
- MySQL

### Implementation Details

- Uses direct JDBC connection to MySQL database
- Console-based user interface with menu options
- Implements basic CRUD operations

## exp_2: Client-Server Architecture

An extension of the movie management system implementing a client-server architecture using Java sockets.

### Features

- Server component that handles database operations
- Client component that sends requests to the server
- Search functionality to find movies by title, director, or genre

### Technologies

- Java
- JDBC
- MySQL
- Java Sockets for network communication

### Implementation Details

- `MovieServer.java`: Handles client connections and database operations
- `MovieClient.java`: Provides user interface and sends requests to the server
- Communication via TCP/IP sockets

## exp_3_final: Servlet-based Web Application

A web-based implementation of the movie management system using Java Servlets.

### Features

- Web interface for movie management
- Servlet-based backend processing
- Database connection pooling

### Technologies

- Java Servlets
- HTML
- JDBC
- MySQL

### Implementation Details

- Servlet classes for handling different operations (add, view, modify, delete)
- HTML forms for user input
- `DBUtil` class for database connection management

## exp_4: JSP-based Web Application

An evolution of the servlet-based application that incorporates JavaServer Pages (JSP).

### Features

- Web interface with JSP pages
- Separation of presentation and business logic
- Form processing with JSP

### Technologies

- Java Servlets
- JavaServer Pages (JSP)
- JDBC
- MySQL

### Implementation Details

- JSP pages for user interface
- HTML forms for data input
- Backend processing with servlets

## exp_5: JSF Web Application

A JavaServer Faces implementation of the movie management system.

### Features

- Component-based UI with JSF
- MVC architecture
- Form validation

### Technologies

- JavaServer Faces (JSF)
- JDBC
- MySQL
- Maven

### Implementation Details

- Model classes for data representation
- DAO layer for database operations
- XHTML pages with JSF components
- JSP pages for alternative views

## exp_6: Custom Tag Library

Demonstrates the use of custom JSP tag libraries in a web application.

### Features

- Custom tag library for CRUD operations
- Simplified JSP page development

### Technologies

- Java Servlets
- JSP
- Custom Tag Libraries
- JDBC
- MySQL

### Implementation Details

- `CrudTagHandler.java`: Custom tag implementation
- JSP pages using custom tags
- Simplified code reuse through tags

## exp_7: Hibernate Experiment

An introduction to Hibernate ORM framework with a simple student management example.

### Features

- Basic Hibernate configuration
- Entity mapping
- Simple CRUD operations

### Technologies

- Hibernate ORM
- Java
- MySQL

### Implementation Details

- Student entity class with Hibernate annotations
- Hibernate configuration
- Basic ORM operations

## exp_8: Advanced Hibernate Implementation

A more advanced implementation using Hibernate with proper DAO pattern and utility classes.

### Features

- Structured Hibernate implementation
- DAO pattern
- Session management

### Technologies

- Hibernate ORM
- Java
- MySQL
- Maven

### Implementation Details

- Entity classes with annotations
- DAO implementation for data access
- Utility classes for Hibernate session management
- Comprehensive CRUD operations

## MovieCRUDApp10: Complete JSF Application

A complete JSF-based movie management application with full CRUD functionality.

### Features

- Full CRUD operations for movies
- JSF component-based UI
- MVC architecture
- Validation and error handling

### Technologies

- JavaServer Faces (JSF) 2.2
- MySQL
- Java 17
- Maven
- Tomcat/GlassFish Server

### Implementation Details

- Model classes for movie data
- DAO layer for database operations
- JSF managed beans
- XHTML pages with JSF components
- Database initialization script

### Setup Instructions

1. Run the `db_init.sql` script to set up the database
2. Configure database connection in `MovieDAO.java`
3. Build and deploy the application
4. Access at http://localhost:8080/MovieCRUDApp10/

## MovieManagementSystem: Spring-based Implementation

A modern implementation of the movie management system using Spring Framework.

### Features

- Spring MVC architecture
- RESTful API endpoints
- Service layer abstraction
- Dependency injection

### Technologies

- Spring Framework
- Spring MVC
- MySQL
- Maven
- Java

### Implementation Details

- Model classes for data representation
- DAO layer for database access
- Service layer for business logic
- Controllers for handling requests
- Dependency injection for component management

## Database Schema

All projects use a similar database schema for the movie table:

```sql
CREATE TABLE movies (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  director VARCHAR(255),
  release_year INT,
  genre VARCHAR(100)
);
```

## System Requirements

- Java JDK (versions vary by project, JDK 8-17)
- MySQL Server
- Apache Tomcat or GlassFish Server (for web applications)
- Maven (for Maven-based projects)
- NetBeans IDE (recommended for easy project management)

## Learning Path

These projects represent a progressive learning path through Java web development technologies:

1. Start with basic JDBC and console applications
2. Move to client-server architecture
3. Progress to web applications with Servlets and JSP
4. Advance to component-based frameworks like JSF
5. Learn ORM with Hibernate
6. Explore modern frameworks like Spring

Each project builds upon concepts from previous ones while introducing new technologies and architectural patterns.
