package com.movieapp.util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/moviedb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    
    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = null;
            try {
                InitialContext ic = new InitialContext();
                DataSource dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/moviedb");
                conn = dataSource.getConnection();
            } catch (NamingException e) {
                conn = java.sql.DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            }
            return conn;
        } catch (SQLException e) {
            throw new SQLException("Database connection error: " + e.getMessage());
        }
    }
    
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}