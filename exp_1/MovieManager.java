
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Movie Management System - JDBC Experiment
 * This program allows users to add, view, modify, and delete movie details
 * using JDBC to interact with a MySQL database.
 */
public class MovieManager {
    // Database connection details
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/moviedb";
    static final String USERNAME = "root";
    static final String PASSWORD = "root";
    static Connection connection = null;

    public static void main(String[] args) {
        try {
            // STEP 1: Load JDBC driver and establish connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Connected to database successfully!");
            
            Scanner scanner = new Scanner(System.in);
            while (true) {
                displayMenu();
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        addMovie();
                        break;
                    case 2:
                        viewMovies();
                        break;
                    case 3:
                        modifyMovie();
                        break;
                    case 4:
                        deleteMovie();
                        break;
                    case 5:
                        System.out.println("Exiting program...");
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found. Please add it to your classpath!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection error!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // STEP 5: Close resources
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Database connection closed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Displays the main menu options
     */
    public static void displayMenu() {
        System.out.println("\n===== MOVIE MANAGEMENT SYSTEM =====");
        System.out.println("1. Add movie");
        System.out.println("2. View movies");
        System.out.println("3. Modify movie details");
        System.out.println("4. Delete movie details");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    /**
     * Adds a new movie to the database
     */
    public static void addMovie() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();
        System.out.print("Enter movie director: ");
        String director = scanner.nextLine();
        System.out.print("Enter movie release year: ");
        int releaseYear = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter movie genre: ");
        String genre = scanner.nextLine();

        // STEP 2 & 3: Create statement and execute SQL
        String query = "INSERT INTO movies (title, director, release_year, genre) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, title);
            statement.setString(2, director);
            statement.setInt(3, releaseYear);
            statement.setString(4, genre);
            
            // STEP 4: Process results
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Movie added successfully!");
            } else {
                System.out.println("Failed to add movie!");
            }
        }
    }

    /**
     * Displays all movies from the database
     */
    public static void viewMovies() throws SQLException {
        // STEP 2 & 3: Create statement and execute SQL
        String query = "SELECT * FROM movies";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            
            // STEP 4: Process results
            boolean hasRecords = false;
            System.out.println("\n===== MOVIE CATALOG =====");
            
            while (resultSet.next()) {
                hasRecords = true;
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Title: " + resultSet.getString("title"));
                System.out.println("Director: " + resultSet.getString("director"));
                System.out.println("Release Year: " + resultSet.getInt("release_year"));
                System.out.println("Genre: " + resultSet.getString("genre"));
                System.out.println("------------------------");
            }
            
            if (!hasRecords) {
                System.out.println("No movies found in database.");
            }
        }
    }

    /**
     * Updates details of an existing movie
     */
    public static void modifyMovie() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter movie title to modify: ");
        String title = scanner.nextLine();
        System.out.print("Enter new director: ");
        String newDirector = scanner.nextLine();

        // STEP 2 & 3: Create statement and execute SQL
        String query = "UPDATE movies SET director = ? WHERE title = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newDirector);
            statement.setString(2, title);
            
            // STEP 4: Process results
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Movie details updated successfully!");
            } else {
                System.out.println("Failed to update movie details! Movie might not exist.");
            }
        }
    }

    /**
     * Deletes a movie from the database
     */
    public static void deleteMovie() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter movie title to delete: ");
        String title = scanner.nextLine();

        // STEP 2 & 3: Create statement and execute SQL
        String query = "DELETE FROM movies WHERE title = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, title);
            
            // STEP 4: Process results
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Movie deleted successfully!");
            } else {
                System.out.println("Failed to delete movie! Movie might not exist.");
            }
        }
    }
}