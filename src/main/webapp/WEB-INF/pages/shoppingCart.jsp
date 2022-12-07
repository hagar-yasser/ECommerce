<%@ page import="org.example.model.CustomerItem" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page import="java.util.List" %><%--<%@ page import="org.example.model.CustomerItem" %>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>SHOPPING CART
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
    <a class="active" href="${pageContext.request.contextPath }/shopping/items/allForAdmin">Home</a>
    <a href="#news">News</a>
    <a href="#contact">Contact</a>
    <a href="#about">About</a>
</div>
<h1>SHOPPING CART</h1>
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
                <td>ACTION</td>


            </tr>
            </thead>
            <tbody>
            <c:if test="${!empty cart}">
                <c:forEach var="customerItem" items="${cart}">
                    <tr>

                        <td>${customerItem.getCustomerItemId().getItem().getName()}</td>
                        <c:if test="${customerItem.getCustomerItemId().getItem().getImage()!=null}">
                            <td><img src=${customerItem.getCustomerItemId().getItem().imageUrlForJSP} alt="wrong Image"
                                width="100 "height="100"/>
                            </td>
                        </c:if>
                        <c:if test="${customerItem.getCustomerItemId().getItem().getImage()==null}">
                            <td>No image</td>
                        </c:if>
                        <td>${customerItem.getCustomerItemId().getItem().getPrice()}</td>
                        <td>${customerItem.getQuantity()}</td>
                        <td>${(customerItem.getCustomerItemId().getItem().getPrice()) * (customerItem.getQuantity())}</td>
                        <td>
                            <a href="${pageContext.request.contextPath }/shopping/cart/delete/${customerItem.getCustomerItemId().getItem().getItemId()}">Delete</a>


                        </td>

                    </tr>


                </c:forEach>

            </c:if>


            </tbody>


            <% int sum = 0;
                List<CustomerItem> customerItemList = (List<CustomerItem>) request.getAttribute("cart");
                for (CustomerItem customerItem : customerItemList) {
                    sum += customerItem.getCustomerItemId().getItem().getPrice() * (customerItem.getQuantity());
                }

            %>


        </table>
        <br>
        <b>
            <h1>

                TOTAL PRICE = ${totalPrice} EGP
                <br>
                <b>

                    <br>
                    <a href="${pageContext.request.contextPath }/shopping/cart/deleteCart/${custometrId}"
                       onclick="return confirm('Are you sure to clear your Cart')">Delete Cart</a>
                    <br>
                </b>
                <br>
                <a href="${pageContext.request.contextPath }/shopping/items/all"><input class="btn btn-primary"
                                                                                        type="submit"
                                                                                        value="continue Shopping"/></a>

                <a href="${pageContext.request.contextPath }/shopping/login/logout"><input class="btn btn-primary"
                                                                                           type="submit"
                                                                                           value="Logout"/></a>
                <a href="<spring:url value="/shopping/orders/submitOrder" />" class="btn btn-primary"><span
                        class="glyphicon glyphicon-shopping-cart"></span> Check out</a>


    </div>
</div>

</body>
</html>
