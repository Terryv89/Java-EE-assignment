<%@ page import="se.distansakademin.models.Project" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Projects index</title>
</head>
<body>
<h1>Projects index</h1>

    <% for (Project project : (List<Project>) request.getAttribute("projects")) { %>
        <p>
            <b> <%= project.getTitle() %> </b> <br>
            <i> <%= project.getDescription() %> </i>
        </p>
    <% } %>

    <p>
        <a href="?action=create">Create project</a>
    </p>

</body>
</html>
