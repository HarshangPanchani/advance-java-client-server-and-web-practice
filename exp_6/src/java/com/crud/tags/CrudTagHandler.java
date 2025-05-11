package com.crud.tags;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class CrudTagHandler extends TagSupport {
    private String operation;
    private String name;
    private String email;
    private String id;
    private String table;
    
    // Database connection parameters
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root"; // Add your password if needed
    
    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTable(String table) {
        this.table = table;
    }
    
    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Execute operation based on attribute
            if ("add".equals(operation)) {
                addRecord(out);
            } else if ("view".equals(operation)) {
                viewRecords(out);
            } else if ("delete".equals(operation)) {
                deleteRecord(out);
            } else if ("modify".equals(operation)) {
                modifyRecord(out);
            } else if ("form".equals(operation)) {
                showForm(out);
            }
        } catch (Exception e) {
            try {
                out.println("<div style='color:red'>Error: " + e.getMessage() + "</div>");
            } catch (IOException ex) {
                throw new JspException(ex);
            }
        }
        
        return SKIP_BODY;
    }
    
    private void addRecord(JspWriter out) throws SQLException, IOException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO " + table + " (name, email) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setString(2, email);
                int result = pstmt.executeUpdate();
                
                if (result > 0) {
                    out.println("<div style='color:green'>Record added successfully!</div>");
                } else {
                    out.println("<div style='color:red'>Failed to add record.</div>");
                }
            }
        }
    }
    
    private void viewRecords(JspWriter out) throws SQLException, IOException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM " + table;
            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                
                out.println("<table border='1'>");
                out.println("<tr><th>ID</th><th>Name</th><th>Email</th><th>Actions</th></tr>");
                
                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("id") + "</td>");
                    out.println("<td>" + rs.getString("name") + "</td>");
                    out.println("<td>" + rs.getString("email") + "</td>");
                    out.println("<td>");
                    out.println("<a href='index.jsp?operation=delete&id=" + rs.getInt("id") + "&table=" + table + "'>Delete</a> | ");
                    out.println("<a href='index.jsp?operation=showEdit&id=" + rs.getInt("id") + "&name=" + rs.getString("name") + "&email=" + rs.getString("email") + "&table=" + table + "'>Edit</a>");
                    out.println("</td>");
                    out.println("</tr>");
                }
                
                out.println("</table>");
            }
        }
    }
    
    private void deleteRecord(JspWriter out) throws SQLException, IOException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "DELETE FROM " + table + " WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, Integer.parseInt(id));
                int result = pstmt.executeUpdate();
                
                if (result > 0) {
                    out.println("<div style='color:green'>Record deleted successfully!</div>");
                } else {
                    out.println("<div style='color:red'>Failed to delete record.</div>");
                }
            }
        }
    }
    
    private void modifyRecord(JspWriter out) throws SQLException, IOException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "UPDATE " + table + " SET name = ?, email = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.setInt(3, Integer.parseInt(id));
                int result = pstmt.executeUpdate();
                
                if (result > 0) {
                    out.println("<div style='color:green'>Record updated successfully!</div>");
                } else {
                    out.println("<div style='color:red'>Failed to update record.</div>");
                }
            }
        }
    }
    
    private void showForm(JspWriter out) throws IOException {
        String formAction = (id != null && !id.isEmpty()) ? "modify" : "add";
        String buttonText = (id != null && !id.isEmpty()) ? "Update Record" : "Add Record";
        
        out.println("<form method='post' action='index.jsp'>");
        out.println("<input type='hidden' name='operation' value='" + formAction + "'>");
        out.println("<input type='hidden' name='table' value='" + table + "'>");
        
        if (id != null && !id.isEmpty()) {
            out.println("<input type='hidden' name='id' value='" + id + "'>");
        }
        
        out.println("<table>");
        out.println("<tr><td>Name:</td><td><input type='text' name='name' value='" + (name != null ? name : "") + "' required></td></tr>");
        out.println("<tr><td>Email:</td><td><input type='email' name='email' value='" + (email != null ? email : "") + "' required></td></tr>");
        out.println("<tr><td colspan='2'><input type='submit' value='" + buttonText + "'></td></tr>");
        out.println("</table>");
        out.println("</form>");
    }
}