package com.movieapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movieapp.util.DBUtil;

@WebServlet("/addMovie")
public class AddMovieServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String director = request.getParameter("director");
        int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
        String genre = request.getParameter("genre");
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Add Movie Result</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 20px; }");
        out.println("h1 { color: #333; }");
        out.println(".menu { display: flex; margin: 20px 0; }");
        out.println(".menu a { background-color: #4CAF50; color: white; padding: 10px 15px; text-decoration: none; margin-right: 10px; border-radius: 4px; }");
        out.println(".menu a:hover { background-color: #45a049; }");
        out.println(".success { color: green; }");
        out.println(".error { color: red; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Add Movie Result</h1>");
        
        out.println("<div class=\"menu\">");
        out.println("<a href=\"index.html\">Home</a>");
        out.println("<a href=\"addMovie.html\">Add Movie</a>");
        out.println("<a href=\"viewMovies\">View Movies</a>");
        out.println("<a href=\"modifyMovie.html\">Modify Movie</a>");
        out.println("<a href=\"deleteMovie.html\">Delete Movie</a>");
        out.println("</div>");
        
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
        
        out.println("</body>");
        out.println("</html>");
    }
}