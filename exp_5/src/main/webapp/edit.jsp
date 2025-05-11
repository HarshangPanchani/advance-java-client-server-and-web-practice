<%@ page import="java.sql.*" %>
<%
    // Get movie ID from request
    int id = 0;
    try {
        id = Integer.parseInt(request.getParameter("id"));
    } catch (NumberFormatException e) {
        // Invalid ID, redirect to view page
        response.sendRedirect("view.jsp");
        return;
    }
    
    // Variables to store movie data
    String title = "";
    String director = "";
    int year = 0;
    
    // Connect to database and fetch movie data
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    try {
        // Load JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // Establish connection
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_db", "root", "");
        
        // Prepare SQL statement
        stmt = conn.prepareStatement("SELECT * FROM movies WHERE id = ?");
        stmt.setInt(1, id);
        
        // Execute query
        rs = stmt.executeQuery();
        
        // Get movie data
        if (rs.next()) {
            title = rs.getString("title");
            director = rs.getString("director");
            year = rs.getInt("year");
        } else {
            // Movie not found, redirect to view page
            response.sendRedirect("view.jsp");
            return;
        }
    } catch (Exception e) {
        out.println("Error: " + e.getMessage());
    } finally {
        // Close resources
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        if (conn != null) conn.close();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Movie Manager - Edit Movie</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #f5f5f5;
        }
        
        h1, h2 {
            color: #333;
        }
        
        .container {
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        
        .form-group {
            margin-bottom: 15px;
        }
        
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        
        .form-group input {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        
        .btn {
            display: inline-block;
            padding: 8px 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            font-size: 14px;
            margin-right: 10px;
        }
        
        .btn:hover {
            background-color: #45a049;
        }
        
        .btn-cancel {
            background-color: #f44336;
        }
        
        .btn-cancel:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Edit Movie</h1>
        
        <form action="update.jsp" method="post">
            <input type="hidden" name="id" value="<%= id %>">
            
            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" id="title" name="title" value="<%= title %>" required>
            </div>
            
            <div class="form-group">
                <label for="director">Director:</label>
                <input type="text" id="director" name="director" value="<%= director %>" required>
            </div>
            
            <div class="form-group">
                <label for="year">Year:</label>
                <input type="number" id="year" name="year" value="<%= year %>" min="1900" max="2100" required>
            </div>
            
            <div class="form-group">
                <input type="submit" value="Update Movie" class="btn">
                <a href="view.jsp" class="btn btn-cancel">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>