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
        <td>IMAGE</td>



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

                <c:if test="${item.image!=null}">
                    <td><img src=${item.imageUrlForJSP} alt="wrong Image" width="100 "height="100"/></td>
                </c:if>

            </tr>
        </c:forEach>

    </c:if>
    </tbody>

</table>
</div>
</div>
<br>
<div>
	<a href="${pageContext.request.contextPath }/shopping/items/""><input class="btn btn-primary" type="submit" value="Search in items" /></a>
</div>
<br>
	<a href="${pageContext.request.contextPath }/shopping/login/logout"><input class="btn btn-primary" type="submit" value="Logout" /></a>
<%

%>

</body>
</html>
