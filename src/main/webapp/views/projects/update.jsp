
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Edit Project</h1>
<form action="projects" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${project.id}">
    <label>Name:</label>
    <input type="text" name="title" value="${project.title}">
    <label>Age:</label>
    <input type="text" name="description" value="${project.description}">
    <button type="submit">Update</button>
    <a href="projects">Cancel</a>
</form>
</body>
</html>
