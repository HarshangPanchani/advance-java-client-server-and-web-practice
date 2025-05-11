package com.example.exp_5.dao;

import com.example.exp_5.model.Movie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Movie entity
 * Handles all database operations related to movies
 */
public class MovieDAO {
    
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/movie_db";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";
    
    // Get database connection
    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }
    
    // Close database resources
    private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println("Error closing database resources: " + e.getMessage());
        }
    }
    
    /**
     * Get all movies from database
     * 
     * @return List of all movies
     */
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM movies ORDER BY id");
            
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setDirector(rs.getString("director"));
                movie.setYear(rs.getInt("year"));
                movies.add(movie);
            }
        } catch (SQLException e) {
            System.err.println("Error getting all movies: " + e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        return movies;
    }
    
    /**
     * Get movie by ID
     * 
     * @param id Movie ID
     * @return Movie object or null if not found
     */
    public Movie getMovieById(int id) {
        Movie movie = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT * FROM movies WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setDirector(rs.getString("director"));
                movie.setYear(rs.getInt("year"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting movie by ID: " + e.getMessage());
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        return movie;
    }
    
    /**
     * Add a new movie to database
     * 
     * @param movie Movie to add
     * @return true if successful, false otherwise
     */
    public boolean addMovie(Movie movie) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement("INSERT INTO movies (title, director, year) VALUES (?, ?, ?)");
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getDirector());
            stmt.setInt(3, movie.getYear());
            
            int rowsAffected = stmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error adding movie: " + e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }
        
        return success;
    }
    
    /**
     * Update an existing movie
     * 
     * @param movie Movie to update
     * @return true if successful, false otherwise
     */
    public boolean updateMovie(Movie movie) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement("UPDATE movies SET title = ?, director = ?, year = ? WHERE id = ?");
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getDirector());
            stmt.setInt(3, movie.getYear());
            stmt.setInt(4, movie.getId());
            
            int rowsAffected = stmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating movie: " + e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }
        
        return success;
    }
    
    /**
     * Delete a movie by ID
     * 
     * @param id Movie ID to delete
     * @return true if successful, false otherwise
     */
    public boolean deleteMovie(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement("DELETE FROM movies WHERE id = ?");
            stmt.setInt(1, id);
            
            int rowsAffected = stmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting movie: " + e.getMessage());
        } finally {
            closeResources(conn, stmt, null);
        }
        
        return success;
    }
}