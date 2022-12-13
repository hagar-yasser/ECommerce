<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Show Order</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<style>
    .topnav {
      overflow: hidden;
      background-color: #333;
      width:device-width;
    }



    .topnav a {
      float: left;
      color: #f2f2f2;
      text-align: center;
      padding: 14px 16px;
      text-decoration: none;
      font-size: 17px;
    }



    .topnav a:hover {
      background-color: #ddd;
      color: black;
    }



    .topnav a.active {
      background-color: #0499aa;
      color: white;
    }
</style>
</head>
<body class="container">
<h1>Orders List</h1>
 <h3 style ="color:red">${error }</h3>
<div class="card">
       <div class="card-body">
<table class="table">
    <thead class="table table-dark">
    <tr>
        <td>Date</td>
        <td>Order_id</td>
        <td>Options</td>


    </tr>
    </thead>
    <tbody>
    <c:if test="${!empty order}">
        <c:forEach var="order" items="${order}">
            <tr>
                <td>${order.myOrderDate}</td>

                <td>${order.myOrderId}</td>

                <td><a href="${pageContext.request.contextPath }/shopping/orders/showItemForOrder/${order.myOrderId}"><input class="btn btn-primary" type="submit" value="Check Order" /></a></td>


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