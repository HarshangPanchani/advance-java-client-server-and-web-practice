<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Movies List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 {
            color: #333;
            border-bottom: 1px solid #ddd;
            padding-bottom: 10px;
        }
        .add-button {
            display: inline-block;
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            text-decoration: none;
            border-radius: 4px;
            margin-bottom: 20px;
        }
        .add-button:hover {
            background-color: #45a049;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .action-links a {
            margin-right: 10px;
            text-decoration: none;
        }
        .edit-link {
            color: #2196F3;
        }
        .delete-link {
            color: #F44336;
        }
        .no-movies {
            margin-top: 20px;
            font-style: italic;
            color: #666;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Movie List</h2>
        
        <a href="${pageContext.request.contextPath}/movies/new" class="add-button">Add New Movie</a>
        
        <c:choose>
            <c:when test="${not empty movies}">
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Director</th>
                        <th>Year</th>
                        <th>Actions</th>
                    </tr>
                    <c:forEach var="movie" items="${movies}">
                        <tr>
                            <td>${movie.id}</td>
                            <td>${movie.title}</td>
                            <td>${movie.director}</td>
                            <td>${movie.year}</td>
                            <td class="action-links">
                                <a href="${pageContext.request.contextPath}/movies/edit/${movie.id}" class="edit-link">Edit</a>
                                <a href="${pageContext.request.contextPath}/movies/delete/${movie.id}" class="delete-link" 
                                   onclick="return confirm('Are you sure you want to delete this movie?')">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p class="no-movies">No movies found in the database.</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>