<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Show Order</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body class="container">
<h1>Admins List</h1>
<div class="card">
       <div class="card-body">
<table class="table">
    <thead class="table table-dark">
    <tr>
        <td>Item id</td>
        <td>category</td>
        <td>name</td>
        <td>price</td>
        <td>quantity</td>
        <td>myOderDate</td>
    </tr>
    </thead>
    <tbody>
    <c:if test="${!empty order}">
        <c:forEach var="order" items="${order}">
            <tr>
                <td>${order.item_id}</td>
                <td>${order.category}</td>
                <td>${order.name}</td>
                <td>${order.price}</td>
                <td>${order.quantity}</td>
                <td>${order.myOrderDate}</td>
            </tr>
        </c:forEach>

    </c:if>
    </tbody>

</table>
<a href="${pageContext.request.contextPath }/shopping/login/logout"><input class="btn btn-primary" type="submit" value="Logout" /></a>
</div>
</div>
</body>
</html>
