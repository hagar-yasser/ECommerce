<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Items</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body class="container">
    <h1>Items</h1>


 <div class="card">
       <div class="card-body">
<table class="table">
    <thead class="table table-dark">
    <tr>
        <td>ID</td>
        <td>NAME</td>
        <td>CATEGORY</td>
        <td>PRICE</td>
        <td>RATING</td>
        <td>QUANTITY</td>
        <td>IMAGE</td>
        <td>OPTIONS</td>






    </tr>
    </thead>
    <tbody>
    <c:if test="${!empty itemsList}">
        <c:forEach var="item" items="${itemsList}">
            <tr>
                <td>${item.itemId}</td>
                <td>${item.name}</td>
                <td>${item.category}</td>
                <td>${item.price}</td>
                <td>${item.rating}</td>
                <td>${item.quantity}</td>
                <c:if test="${item.image!=null}">
                    <td><img src=${item.imageUrlForJSP} alt="wrong Image" width="100 "height="100"/></td>
                </c:if>
                <c:if test="${item.image ==null}">
                       <td></td>
                </c:if>

                 <td>
                   <a href="${pageContext.request.contextPath }/shopping/admin/updateItem/${item.itemId}">Update</a>
                   | <a href="${pageContext.request.contextPath }/shopping/admin/deleteItem/${item.itemId}" onclick="return confirm('Are you sure?')">Delete</a>
                    </td>

            </tr>
        </c:forEach>

    </c:if>
    </tbody>

</table>
</div>
</div>
<%

%>

</body>
</html>
