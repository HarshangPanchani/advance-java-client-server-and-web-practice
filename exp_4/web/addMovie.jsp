<%@ page import="java.sql.*" %>
<%@ page import="com.movieapp.util.DBUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Movie Result</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1 {
            color: #333;
        }
        .menu {
            display: flex;
            margin: 20px 0;
        }
        .menu a {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            text-decoration: none;
            margin-right: 10px;
            border-radius: 4px;
        }
        .menu a:hover {
            background-color: #45a049;
        }
        .success {
            color: green;
        }
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <h1>Add Movie Result</h1>
    
    <div class="menu">
        <a href="index.html">Home</a>
        <a href="addMovie.html">Add Movie</a>
        <a href="viewMovies.jsp">View Movies</a>
        <a href="modifyMovie.html">Modify Movie</a>
        <a href="deleteMovie.html">Delete Movie</a>
    </div>
    
    <%
        String title = request.getParameter("title");
        String director = request.getParameter("director");
        int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
        String genre = request.getParameter("genre");
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO movies (title, director, release_year, genre) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.setString(2, director);
            stmt.setInt(3, releaseYear);
            stmt.setString(4, genre);
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                out.println("<p class=\"success\">Movie added successfully!</p>");
            } else {
                out.println("<p class=\"error\">Failed to add movie!</p>");
            }
        } catch (SQLException e) {
            out.println("<p class=\"error\">Database error: " + e.getMessage() + "</p>");
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DBUtil.closeConnection(conn);
        }
    %>
</body>
</html>