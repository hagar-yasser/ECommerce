<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Items</title>
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
<div class="topnav">
  <a class="active" href="${pageContext.request.contextPath }/shopping/items/all">Home</a>
  <a href="#news">News</a>
  <a href="#contact">Contact</a>
  <a href="#about">About</a>
</div>
    <h1>Items</h1>
<h3 style ="color:red">${error }</h3>

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
        <td>QUANTITY</td>
        <td>ACTION</td>



    </tr>
    </thead>
    <tbody>

    <c:if test="${!empty itemsList}">
        <c:forEach var="item" items="${itemsList}">
            <form method="post" action="${pageContext.request.contextPath }/shopping/cart/addItem/${item.itemId}">
            <tr>
                <td>${item.itemId}</td>
                <td>${item.name}</td>
                <td>${item.category}</td>
                <td>${item.price}</td>
                <td>${item.rating}</td>

                <c:if test="${item.image!=null}">
                    <td><img src=${item.imageUrlForJSP} alt="wrong Image" width="100 "height="100"/></td>
                </c:if>
                <c:if test="${item.image==null}">
                     <td>No image</td>
                </c:if>

                <td><input class="form-control" type="number" name="quantity" required="required" min="0"/></td>

                <td><input class="btn btn-primary" type="submit" value="add to cart" /></td>

            </tr>
            </form>
        </c:forEach>

    </c:if>
    </tbody>

</table>
<a href="${pageContext.request.contextPath }/shopping/items/"><input class="btn btn-primary" type="submit" value="Search in items" /></a>
<a href="${pageContext.request.contextPath }/shopping/login/logout"><input class="btn btn-primary" type="submit" value="Logout" /></a>
           <a href="<spring:url value="/shopping/cart/showAll" />" class="btn btn-success pull-right"><span
                   class="glyphicon glyphicon-shopping-cart"></span>Show Cart</a>
</div>
</div>

</body>
</html>
