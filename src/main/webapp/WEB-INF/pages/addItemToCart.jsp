<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%    request.setCharacterEncoding("UTF8");   %>
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

  <form:form modelAttribute="customerItem"  method="post" >
    <div class="from-row md-4">
      <label for="customerItem.customerItemId.item.itemId">item Id</label>
      <form:input path="customerItem.customerItemId.item.itemId" cssClass="form-control" id="itemId"/>
      <form:errors path="customerItem.customerItemId.item.itemId" cssClass="error"/>
    </div>
    <div class="from-row md-4">
      <label for="customerItem.customerItemId.customer.customerId">item Id</label>
      <form:input path="customerItem.customerItemId.customer.customerId" cssClass="form-control" id="itemId"/>
      <form:errors path="customerItem.customerItemId.customer.customerId" cssClass="error"/>
    </div>
    <div class="from-row">
      <label for="quantity">quantity </label>
      <form:input path="quantity" cssClass="form-control" id="quantity" type="number"/>
      <form:errors path="quantity" cssClass="error"/>
    </div>

    <br>
    <input type="submit" value="add Item">
  </form:form>

</div>
</body>
</html>




<%--<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>--%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ page pageEncoding="UTF-8" %>--%>
<%--<%    request.setCharacterEncoding("UTF8");   %>--%>
<%--<html xmlns:spring="http://www.springframework.org/tags" xmlns:form="http://www.springframework.org/tags/form">--%>
<%--<head>--%>
<%--  <title>Registration Form</title>--%>
<%--  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">--%>
<%--  <style>--%>
<%--    span.error {--%>
<%--      color: red;--%>
<%--      display: inline-block;--%>
<%--    }--%>
<%--  </style>--%>

<%--</head>--%>
<%--<body>--%>
<%--<div class="container">--%>

<%--  <form:form modelAttribute="item"  method="post" >--%>
<%--    <div class="from-row md-4">--%>
<%--      <label for="itemId">item Id</label>--%>
<%--      <form:input path="itemId" cssClass="form-control" id="itemId"/>--%>
<%--      <form:errors path="itemId" cssClass="error"/>--%>
<%--    </div>--%>
<%--    <div class="from-row">--%>
<%--      <label for="quantity">quantity </label>--%>
<%--      <form:input path="quantity" cssClass="form-control" id="quantity" type="number"/>--%>
<%--      <form:errors path="quantity" cssClass="error"/>--%>
<%--    </div>--%>

<%--    <br>--%>
<%--    <input type="submit" value="add Item">--%>
<%--  </form:form>--%>

<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
