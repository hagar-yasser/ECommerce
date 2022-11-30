<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>all users</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body class="container">
<table class="table">
    <thead class="table table-dark">
    <tr>
        <td>ID</td>
        <td>firstName</td>
        <td>lastName</td>
        <td>Email</td>
        <td>PassWord</td>
        <td>isAdmin</td>
        <td>isLoggedIn</td>
        <td>isActivated</td>
        <td>wrongPasswordTrials</td>
        <td>Options</td>
    </tr>
    </thead>
    <tbody>
    <c:if test="${!empty admins}">
        <c:forEach var="user" items="${admins}">
            <tr>
                <td>${user.customerId}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>${user.password}</td>
                <td>${user.isAdmin}</td>
                <td>${user.isLoggedIn}</td>
                <td>${user.isActivated}</td>
                <td>${user.wrongPasswordTrials}</td>
                <td>
                   <a href="${pageContext.request.contextPath }/shopping/admin/deleteAdmin/${user.customerId }" onclick="return confirm('Are you sure?')">Delete</a>
                </td>
            </tr>
        </c:forEach>

    </c:if>
    </tbody>

</table>
</body>
</html>
