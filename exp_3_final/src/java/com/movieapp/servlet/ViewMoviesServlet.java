package com.movieapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movieapp.util.DBUtil;

@WebServlet("/viewMovies")
public class ViewMoviesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>View Movies</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 20px; }");
        out.println("h1 { color: #333; }");
        out.println(".menu { display: flex; margin: 20px 0; }");
        out.println(".menu a { background-color: #4CAF50; color: white; padding: 10px 15px; text-decoration: none; margin-right: 10px; border-radius: 4px; }");
        out.println(".menu a:hover { background-color: #45a049; }");
        out.println("table { border-collapse: collapse; width: 100%; }");
        out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
        out.println("th { background-color: #4CAF50; color: white; }");
        out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
        out.println(".error { color: red; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Movie Catalog</h1>");
        
        out.println("<div class=\"menu\">");
        out.println("<a href=\"index.html\">Home</a>");
        out.println("<a href=\"addMovie.html\">Add Movie</a>");
        out.println("<a href=\"viewMovies\">View Movies</a>");
        out.println("<a href=\"modifyMovie.html\">Modify Movie</a>");
        out.println("<a href=\"deleteMovie.html\">Delete Movie</a>");
        out.println("</div>");
        
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
        
        out.println("</body>");
        out.println("</html>");
    }
}