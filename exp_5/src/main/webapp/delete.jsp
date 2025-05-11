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
    // Delete movie from database
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try {
        // Load JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // Establish connection
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_db", "root", "");
        
        // Prepare SQL statement
        pstmt = conn.prepareStatement("DELETE FROM movies WHERE id = ?");
        pstmt.setInt(1, id);
        
        // Execute SQL statement
        pstmt.executeUpdate();
        
        // Redirect to view page
        response.sendRedirect("view.jsp");
    } catch (Exception e) {
        out.println("Error: " + e.getMessage());
    } finally {
        // Close resources
        if (pstmt != null) pstmt.close();
        if (conn != null) conn.close();
    }
%>