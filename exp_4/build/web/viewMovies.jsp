<%@ page import="java.sql.*" %>
<%@ page import="com.movieapp.util.DBUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Movies</title>
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
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <h1>Movie Catalog</h1>
    
    <div class="menu">
        <a href="index.html">Home</a>
        <a href="addMovie.html">Add Movie</a>
        <a href="viewMovies.jsp">View Movies</a>
        <a href="modifyMovie.html">Modify Movie</a>
        <a href="deleteMovie.html">Delete Movie</a>
    </div>
    
    <%
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM movies";
            rs = stmt.executeQuery(sql);
            
            boolean hasRecords = false;
            out.println("<table>");
            out.println("<tr><th>ID</th><th>Title</th><th>Director</th><th>Release Year</th><th>Genre</th></tr>");
            
            while (rs.next()) {
                hasRecords = true;
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("title") + "</td>");
                out.println("<td>" + rs.getString("director") + "</td>");
                out.println("<td>" + rs.getInt("release_year") + "</td>");
                out.println("<td>" + rs.getString("genre") + "</td>");
                out.println("</tr>");
            }
            
            out.println("</table>");
            
            if (!hasRecords) {
                out.println("<p>No movies found in database.</p>");
            }
            
        } catch (SQLException e) {
            out.println("<p class=\"error\">Database error: " + e.getMessage() + "</p>");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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