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
<form method="get" action="${pageContext.request.contextPath }/shopping/admin/updateItem/">
               <tr>
               <td>Name:</td>
               <td><input class="form-control" type="number" name="itemId"/></td>
               <td><input class="btn btn-primary"type="submit" value="Update Item" /></td>
               </tr>
               </form>
                </body>

               </html>