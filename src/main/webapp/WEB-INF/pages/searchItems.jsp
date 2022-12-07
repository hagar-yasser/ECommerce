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
               <form method="get" action="${pageContext.request.contextPath }/shopping/items/searchConjunction">
               <tr>
               <td>Name:</td>
               <td><input class="form-control" type="text" name="name"/></td>
               </tr>
               <tr>
               <td>Category:</td>
               <td><input class="form-control" type="text" name="category"/></td>
               </tr>
               <tr>
               <td>Rating:</td>
               <td><input class="form-control" type="text" name="rating"/></td>
               </tr>
               <tr>
               <td>Price:</td>
               <td><input class="form-control" type="text" name="price"/></td>
               </tr>
               <input class="btn btn-primary" type="submit" value="Show Items" />
               </form>
                        </tbody>

               </table>

               <a href="${pageContext.request.contextPath }/shopping/login/logout"><input class="btn btn-primary" type="submit" value="Logout" /></a>


    </body>

</html>