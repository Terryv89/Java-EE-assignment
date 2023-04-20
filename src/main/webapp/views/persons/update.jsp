<%@ page import="se.distansakademin.models.Person" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%var person = (Person) request.getAttribute("person"); %>
<html>
<head>
    <title>update person</title>
</head>
<body>
<h1>Update person</h1>
<form action="persons" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${person.id}">
    <label>Name:</label>
    <input type="text" name="name" value="${person.name}">
    <label>Age:</label>
    <input type="text" name="lastName" value="${person.lastName}">
    <button type="submit">Update</button>
    <a href="persons">Cancel</a>
</form>
<br>
<form action="${pageContext.request.contextPath}/persons"method="post">
    <input type="hidden" name="action" value="delete">
    <input type="hidden" name="id" value="<%=person.getId()%>">

    <input type="submit" value="Delete"></form>
</body>
</html>