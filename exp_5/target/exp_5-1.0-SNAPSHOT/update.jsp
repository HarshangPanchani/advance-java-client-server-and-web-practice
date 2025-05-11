<%@ page import="java.sql.*" %>
<%
    // Get form data
    String idStr = request.getParameter("id");
    String title = request.getParameter("title");
    String director = request.getParameter("director");
    String yearStr = request.getParameter("year");
    
    // Validate form data
    boolean isValid = true;
    String errorMessage = "";
    
    int id = 0;
    int year = 0;
    
    if (idStr == null || idStr.trim().isEmpty()) {
        isValid = false;
        errorMessage += "ID is required.<br>";
    } else {
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            isValid = false;
            errorMessage += "Invalid ID.<br>";
        }
    }
    
    if (title == null || title.trim().isEmpty()) {
        isValid = false;
        errorMessage += "Title is required.<br>";
    }
    
    if (director == null || director.trim().isEmpty()) {
        isValid = false;
        errorMessage += "Director is required.<br>";
    }
    
    if (yearStr == null || yearStr.trim().isEmpty()) {
        isValid = false;
        errorMessage += "Year is required.<br>";
    } else {
        try {
            year = Integer.parseInt(yearStr);
            if (year < 1900 || year > 2100) {
                isValid = false;
                errorMessage += "Year must be between 1900 and 2100.<br>";
            }
        } catch (NumberFormatException e) {
            isValid = false;
            errorMessage += "Year must be a valid number.<br>";
        }
    }
    
    // If form data is valid, update database
    if (isValid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_db", "root", "");
            
            // Prepare SQL statement
            pstmt = conn.prepareStatement("UPDATE movies SET title = ?, director = ?, year = ? WHERE id = ?");
            pstmt.setString(1, title);
            pstmt.setString(2, director);
            pstmt.setInt(3, year);
            pstmt.setInt(4, id);
            
            // Execute SQL statement
            int rowsAffected = pstmt.executeUpdate();
            
            // Redirect to view page
            if (rowsAffected > 0) {
                response.sendRedirect("view.jsp");
                return;
            } else {
                errorMessage = "Failed to update movie. Please try again.";
            }
        } catch (Exception e) {
            errorMessage = "Error: " + e.getMessage();
        } finally {
            // Close resources
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Movie Manager - Error</title>
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
        
        .error-box {
            background-color: #f2dede;
            border: 1px solid #ebccd1;
            color: #a94442;
            padding: 15px;
            border-radius: 4px;
            margin-bottom: 20px;
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
        }
        
        .btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Error Updating Movie</h1>
        
        <div class="error-box">
            <%= errorMessage %>
        </div>
        
        <a href="view.jsp" class="btn">Back to Movies</a>
    </div>
</body>
</html>