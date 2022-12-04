<%@ page import="org.example.model.CustomerItem" %>
<%@ page import="org.springframework.ui.Model" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>SHOPPING CART</title>
    <h1>Cart</h1>
    <p>All the selected products in your shopping cart</p>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body class="container">

<table class="table">
    <thead class="table table-dark">
    <tr>
        <td>PRODUCT</td>
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
                <td>${customerItem.getCustomerItemId().getItem().getPrice()}</td>
                <td>${customerItem.getQuantity()}</td>
                <td>${(customerItem.getCustomerItemId().getItem().getPrice()) * (customerItem.getQuantity())}</td>
                    <td>
                        <a href="${pageContext.request.contextPath }/shopping/Cart/delete/${customerItem.getCustomerItemId().getItem().getItemId()}" >Delete</a>
                    </td>

            </tr>



            </c:forEach>

    </c:if>

<%--    <% int sum =0 ;--%>
<%--        List<CustomerItem> customerItemList= (List<CustomerItem>) request.getAttribute("cart");--%>
<%--        for ( CustomerItem customerItem : customerItemList)--%>
<%--        {--%>
<%--            sum += customerItem.getCustomerItemId().getItem().getPrice() * (customerItem.getQuantity());--%>
<%--        }--%>

<%--    %>--%>
 </tbody>

</table>
    <br>
<b>
    <h1>
        <b>
            <a href="${pageContext.request.contextPath }/shopping/Cart/deleteCart/${custometrId}" onclick="return confirm('Are you sure to clear your Cart')">Delete Cart</a>
        </b>
        TOTAL PRICE = ${totalPrice} EGP
        <br>
    <a href="${pageContext.request.contextPath }/shopping/items/all" >continue Shopping</a>
        <br>
        <a href="${pageContext.request.contextPath }/shopping/orders/submitOrder/" >check out</a>

    </h1>
</b>
<br>
<b>

</body>
</html>
