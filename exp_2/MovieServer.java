import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieServer {
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/moviedb";
    static final String USERNAME = "root";
    static final String PASSWORD = "root";
    static final int PORT = 5000;
    static Connection connection = null;

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Connected to database successfully!");
            
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started on port " + PORT);
            System.out.println("Waiting for client connections...");
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
                
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                
                String searchQuery = in.readLine();
                System.out.println("Received search query: " + searchQuery);
                
                String result = searchMovies(searchQuery);
                out.println(result);
                
                System.out.println("Sent response to client");
                clientSocket.close();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found. Please add it to your classpath!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection error!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
            e.printStackTrace();
        } finally {
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
    
    static String searchMovies(String searchQuery) {
        StringBuilder result = new StringBuilder();
        
        try {
            String query = "SELECT title FROM movies WHERE title LIKE ? OR director LIKE ? OR genre LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchQuery + "%");
            statement.setString(2, "%" + searchQuery + "%");
            statement.setString(3, "%" + searchQuery + "%");
            
            ResultSet resultSet = statement.executeQuery();
            
            boolean found = false;
            while (resultSet.next()) {
                if (found) {
                    result.append(", ");
                }
                result.append(resultSet.getString("title"));
                found = true;
            }
            
            if (!found) {
                result.append("No movies found matching: ").append(searchQuery);
            }
            
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            result.append("Error searching movies: ").append(e.getMessage());
            e.printStackTrace();
        }
        
        return result.toString();
    }
}