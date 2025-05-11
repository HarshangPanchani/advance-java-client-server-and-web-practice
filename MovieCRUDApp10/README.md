# Movie CRUD Application with JSF

This is a JavaServer Faces (JSF) application that implements CRUD operations for a movie database. The application demonstrates the MVC architecture using JSF framework.

## Technologies Used
- JavaServer Faces (JSF) 2.2
- MySQL Database
- Java 17
- Maven
- Tomcat/GlassFish Server

## Setup Instructions

### Database Setup
1. Open MySQL Workbench
2. Run the `db_init.sql` script to create the database, table, and insert sample data
3. Verify the database connection details in `MovieDAO.java`:
   - Database: movie_db
   - Username: root 
   - Password: root (update with your MySQL password if different)

### Project Setup
1. Open the project in NetBeans
2. Make sure you have JDK 17 configured
3. Make sure your MySQL Connector JAR is in the project classpath (added via Maven)
4. Clean and build the project

### Running the Application
1. Right-click on the project and select "Run"
2. The application will be deployed to the server
3. Access the application at: http://localhost:8080/MovieCRUDApp10/

## CRUD Operations
- View movies: Home page displays all movies in the database
- Add movie: Click "Add New Movie" button and fill in the form
- Edit movie: Click "Edit" button for a movie and update its details
- Delete movie: Click "Delete" button to remove a movie

## JSF vs Spring Framework Comparison

### JSF Features
- Component-based UI framework
- Built-in validation and conversion
- Rich component libraries
- Strong integration with JSF ecosystem
- Managed bean lifecycle

### Spring MVC Features
- Clean separation of concerns
- Lightweight and modular
- Extensive support for RESTful services
- More flexibility and configuration options
- Better integration with other Spring projects

### Advantages of JSF
- Simplifies UI components development
- Built-in Ajax support
- State management
- Quick development for form-based applications

### Advantages of Spring
- Better for microservices architecture
- More control and flexibility
- Better for large enterprise applications
- More modern web development approaches 