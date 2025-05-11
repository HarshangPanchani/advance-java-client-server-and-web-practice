<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="crud" uri="/WEB-INF/tld/crud" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>CRUD Operations with Custom Tags</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        table {
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        .container {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }
    </style>
</head>
<body>
    <h1>CRUD Operations with Custom Tags</h1>
    <div class="container">
        <%
            String operation = request.getParameter("operation");
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String table = "users"; // Default table name
            
            if (request.getParameter("table") != null) {
                table = request.getParameter("table");
            }
            
            if (operation != null) {
                if (operation.equals("add") || operation.equals("modify")) {
                    // Process form submissions
        %>
                    <crud:database 
                        operation="<%= operation %>"
                        name="<%= name %>"
                        email="<%= email %>"
                        id="<%= id %>"
                        table="<%= table %>"
                    />
        <%
                } else if (operation.equals("delete")) {
                    // Handle delete operation
        %>
                    <crud:database 
                        operation="delete"
                        id="<%= id %>"
                        table="<%= table %>"
                    />
        <%
                } else if (operation.equals("showEdit")) {
                    // Show edit form
        %>
                    <h2>Edit Record</h2>
                    <crud:database 
                        operation="form"
                        id="<%= id %>"
                        name="<%= name %>"
                        email="<%= email %>"
                        table="<%= table %>"
                    />
        <%
                }
            }
            
            // Show add form by default
            if (operation == null || !operation.equals("showEdit")) {
        %>
                <h2>Add New Record</h2>
                <crud:database 
                    operation="form"
                    table="<%= table %>"
                />
        <%
            }
        %>
        
        <h2>Current Records</h2>
        <crud:database operation="view" table="<%= table %>" />
    </div>
</body>
</html>