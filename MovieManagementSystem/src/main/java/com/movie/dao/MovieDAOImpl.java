package com.movie.dao;

import com.movie.model.Movie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDAOImpl implements MovieDAO {
    // Database connection details
    private String jdbcURL = "jdbc:mysql://localhost:3306/movie_db?useSSL=false&allowPublicKeyRetrieval=true";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root"; // Change if you have password
    
    // SQL queries
    private static final String INSERT_MOVIE_SQL = 
            "INSERT INTO movies (title, director, year) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_MOVIES = 
            "SELECT * FROM movies";
    private static final String SELECT_MOVIE_BY_ID = 
            "SELECT * FROM movies WHERE id = ?";
    private static final String UPDATE_MOVIE_SQL = 
            "UPDATE movies SET title = ?, director = ?, year = ? WHERE id = ?";
    private static final String DELETE_MOVIE_SQL = 
            "DELETE FROM movies WHERE id = ?";
    
    // Method to get database connection
    protected Connection getConnection() {
        Connection connection = null;
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Create the connection
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            
            if (connection == null) {
                System.err.println("Failed to establish a connection to the database.");
            } else {
                System.out.println("Database connection established successfully.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception while connecting to database: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error while connecting to database: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
    
    // Method to save a new movie
    @Override
    public void saveMovie(Movie movie) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MOVIE_SQL)) {
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, movie.getDirector());
            preparedStatement.setInt(3, movie.getYear());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Method to get all movies
    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = getConnection()) {
            if (connection == null) {
                System.err.println("ERROR: Unable to connect to database in getAllMovies()");
                return movies; // Return empty list instead of causing NullPointerException
            }
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MOVIES)) {
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    Movie movie = new Movie();
                    movie.setId(rs.getInt("id"));
                    movie.setTitle(rs.getString("title"));
                    movie.setDirector(rs.getString("director"));
                    movie.setYear(rs.getInt("year"));
                    movies.add(movie);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL error in getAllMovies(): " + e.getMessage());
            e.printStackTrace();
        }
        return movies;
    }
    
    // Method to get a movie by ID
    @Override
    public Movie getMovieById(int id) {
        Movie movie = null;
        try (Connection connection = getConnection()) {
            if (connection == null) {
                System.err.println("ERROR: Unable to connect to database in getMovieById()");
                return null;
            }
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MOVIE_BY_ID)) {
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    movie = new Movie();
                    movie.setId(rs.getInt("id"));
                    movie.setTitle(rs.getString("title"));
                    movie.setDirector(rs.getString("director"));
                    movie.setYear(rs.getInt("year"));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL error in getMovieById(): " + e.getMessage());
            e.printStackTrace();
        }
        return movie;
    }
    
    // Method to update a movie
    @Override
    public void updateMovie(Movie movie) {
        try (Connection connection = getConnection()) {
            if (connection == null) {
                System.err.println("ERROR: Unable to connect to database in updateMovie()");
                return;
            }
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MOVIE_SQL)) {
                preparedStatement.setString(1, movie.getTitle());
                preparedStatement.setString(2, movie.getDirector());
                preparedStatement.setInt(3, movie.getYear());
                preparedStatement.setInt(4, movie.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("SQL error in updateMovie(): " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Method to delete a movie
    @Override
    public void deleteMovie(int id) {
        try (Connection connection = getConnection()) {
            if (connection == null) {
                System.err.println("ERROR: Unable to connect to database in deleteMovie()");
                return;
            }
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MOVIE_SQL)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("SQL error in deleteMovie(): " + e.getMessage());
            e.printStackTrace();
        }
    }
}