<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:spring="http://www.springframework.org/tags" xmlns:form="http://www.springframework.org/tags/form">
<head>
    <title>Registration Form</title>
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
    <form:form modelAttribute="customerIdDTO" method="post">
        <div class="from-row md-4">
            <label for="customerId">customer id</label>
            <form:input path="customerId" cssClass="form-control" id="customerId" type="number"/>
        </div>
        <br>
        <input type="submit" value="add shopping cart to order">
    </form:form>

</div>
</body>
</html>
