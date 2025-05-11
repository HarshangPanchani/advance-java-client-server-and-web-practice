<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Movie Manager - All Movies</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 1000px;
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
        
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }
        
        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        
        tr:hover {
            background-color: #f1f1f1;
        }
        
        .btn {
            display: inline-block;
            padding: 6px 12px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            font-size: 14px;
            margin-right: 5px;
        }
        
        .btn:hover {
            background-color: #45a049;
        }
        
        .btn-edit {
            background-color: #ff9800;
        }
        
        .btn-edit:hover {
            background-color: #fb8c00;
        }
        
        .btn-delete {
            background-color: #f44336;
        }
        
        .btn-delete:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>All Movies</h1>
        
        <table>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Director</th>
                <th>Year</th>
                <th>Actions</th>
            </tr>
            <%
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                
                try {
                    // Load JDBC driver
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    
                    // Establish connection
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_db", "root", "");
                    
                    // Create statement
                    stmt = conn.createStatement();
                    
                    // Execute query
                    rs = stmt.executeQuery("SELECT * FROM movies");
                    
                    // Display results
                    while (rs.next()) {
            %>
            <tr>
                <td><%= rs.getInt("id") %></td>
                <td><%= rs.getString("title") %></td>
                <td><%= rs.getString("director") %></td>
                <td><%= rs.getInt("year") %></td>
                <td>
                    <a href="edit.jsp?id=<%= rs.getInt("id") %>" class="btn btn-edit">Edit</a>
                    <a href="delete.jsp?id=<%= rs.getInt("id") %>" class="btn btn-delete" onclick="return confirm('Are you sure you want to delete this movie?');">Delete</a>
                </td>
            </tr>
            <%
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
        </table>
        
        <a href="index.html" class="btn">Back to Home</a>
        
        <div style="margin-top: 20px;">
            <h2>JSF Version</h2>
            <p>This application is also available with JSF (Java Server Faces):</p>
            <a href="index.xhtml" class="btn">Go to JSF Version</a>
        </div>
    </div>
</body>
</html>