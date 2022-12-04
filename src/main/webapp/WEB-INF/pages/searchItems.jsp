<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:spring="http://www.springframework.org/tags" xmlns:form="http://www.springframework.org/tags/form">


    <head>
        <title>Search Items</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <style>
                span.error {
                    color: red;
                    display: inline-block;
                }
        </style>

    </head>

    <body>




    <div class="container">
    <h1>Search In Items</h1>


      <div class="card">
       <div class="card-body">
        <table class="table">
                    <tbody>
               <form method="get" action="${pageContext.request.contextPath }/shopping/items/all">
               <tr>
               <td><input class="btn btn-primary" type="submit" value="Show All Items" /></td>
               </tr>
               </form>

               <form method="get" action="${pageContext.request.contextPath }/shopping/items/name">
               <tr>
               <td>Name:</td>
               <td><input class="form-control" type="text" name="name"/></td>
               <td><input class="btn btn-primary"type="submit" value="Filter Items By Name" /></td>
               </tr>
               </form>

               <form method="get" action="${pageContext.request.contextPath }/shopping/items/category">
               <tr>
               <td>Category:</td>
               <td><input class="form-control" type="text" name="category"/></td>
               <td><input class="btn btn-primary" type="submit" value="Filter Items By Category" /></td>
               </tr>
               </form>

               <form method="get" action="${pageContext.request.contextPath }/shopping/items/rating">
               <tr>
               <td>Rating:</td>
               <td><input class="form-control" type="text" name="rating"/></td>
               <td><input class="btn btn-primary" type="submit" value="Filter Items By Rating" /></td>
               </tr>
               </form>
               <form method="get" action="${pageContext.request.contextPath }/shopping/items/price">
               <tr>
               <td>Price:</td>
               <td><input class="form-control" type="text" name="price"/></td>
               <td><input class="btn btn-primary" type="submit" value="Filter Items By Price" /></td>
               </tr>
               </form>
                        </tbody>

               </table>


    </body>

</html>