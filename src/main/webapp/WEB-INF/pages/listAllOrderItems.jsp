<%@ page import="org.example.model.MyOrderItem" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page import="java.util.List" %><%--<%@ page import="org.example.model.MyOrderItem" %>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title> Orders
    </title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <style>
        .topnav {
            overflow: hidden;
            background-color: #333;
            width: device-width;
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
<h1>Order</h1>
<h3 style="color:red">${error }</h3>

<div class="card">
    <div class="card-body">
        <table class="table">
            <thead class="table table-dark">
            <tr>
                <td>PRODUCT</td>
                <td>IMAGE</td>
                <td>UNIT PRICE</td>
                <td>QUANTITY</td>
                <td>PRICE</td>


            </tr>
            </thead>
            <tbody>
            <c:if test="${!empty orderItems}">
                <c:forEach var="orderItem" items="${orderItems}">
                    <tr>

                        <td>${orderItem.getMyOrderItemId().getItem().getName()}</td>
                        <c:if test="${orderItem.getMyOrderItemId().getItem().getImage()!=null}">
                            <td><img src=${orderItem.getMyOrderItemId().getItem().imageUrlForJSP} alt="wrong Image"
                                width="100 "height="100"/>
                            </td>
                        </c:if>
                        <c:if test="${orderItem.getMyOrderItemId().getItem().getImage()==null}">
                            <td>No image</td>
                        </c:if>
                        <td>${orderItem.getMyOrderItemId().getItem().getPrice()}</td>
                        <td>${orderItem.getQuantity()}</td>
                        <td>${(orderItem.getMyOrderItemId().getItem().getPrice()) * (orderItem.getQuantity())}</td>


                    </tr>


                </c:forEach>

            </c:if>


            </tbody>



        </table>
        <br>
        <b>
            <h1>

                TOTAL PRICE = ${totalPrice} EGP
                <br>
                <b>

                <a href="${pageContext.request.contextPath }/shopping/login/logout"><input class="btn btn-primary"
                                                                                           type="submit"
                                                                                           value="Logout"/></a>


    </div>
</div>

</body>
</html>
