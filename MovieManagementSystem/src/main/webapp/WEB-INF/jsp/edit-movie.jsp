<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Movie</title>
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
        form {
            width: 100%;
            margin-top: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #2196F3;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #0b7dda;
        }
        .back-link {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            color: #2196F3;
        }
        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Edit Movie</h2>
        
        <form action="${pageContext.request.contextPath}/movies/update" method="post">
            <input type="hidden" name="id" value="${movie.id}" />
            
            <div>
                <label for="title">Title:</label>
                <input type="text" id="title" name="title" value="${movie.title}" required />
            </div>
            
            <div>
                <label for="director">Director:</label>
                <input type="text" id="director" name="director" value="${movie.director}" required />
            </div>
            
            <div>
                <label for="year">Year:</label>
                <input type="number" id="year" name="year" value="${movie.year}" min="1900" max="2100" required />
            </div>
            
            <div>
                <input type="submit" value="Update" />
            </div>
        </form>
        
        <a href="${pageContext.request.contextPath}/movies" class="back-link">Back to Movies List</a>
    </div>
</body>
</html>