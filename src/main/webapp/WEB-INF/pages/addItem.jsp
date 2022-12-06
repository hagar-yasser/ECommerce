<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%    request.setCharacterEncoding("UTF8");   %>
<html xmlns:spring="http://www.springframework.org/tags" xmlns:form="http://www.springframework.org/tags/form">
<head>
    <title>Add Item Form</title>
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
<h1>Add New Item</h1>
<h3 style ="color:red">${error }</h3>
<div class="card">
       <div class="card-body">
<%--           method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/shopping/admin/addItem"--%>
    <form:form modelAttribute="item" method="post" action="${pageContext.request.contextPath}/shopping/admin/addItem">

        <div class="from-row md-4">
            <label for="name">Name</label>
            <form:input path="name" cssClass="form-control" id="name"/>
        </div>
        <div class="from-row md-4">
            <label for="category">Category</label>
            <form:input path="category" cssClass="form-control" id="category"/>
       </div>
       <div class="from-row md-4">
           <label for="price">Price</label>
           <form:input path="price" cssClass="form-control" id="price"/>
      </div>
      <div class="from-row md-4">
         <label for="quantity">Quantity</label>
         <form:input path="quantity" cssClass="form-control" id="quantity"/>
    </div>
    <div class="from-row md-4">
     <label for="rating">Rating</label>
     <form:input path="rating" cssClass="form-control" id="rating"/>
    </div>
<%--    <div class="from-row md-4">--%>
<%--     <label for="image">Select an image to upload</label>--%>
<%--     <input type="file" cssClass="form-control" name="image"/>--%>
<%--    </div>--%>


        <br>
        <input type="submit" value="add Item" class="btn btn-primary">
    </form:form>
        <a href="${pageContext.request.contextPath }/shopping/login/logout"><input class="btn btn-primary" type="submit" value="Logout" /></a>

</div>
</div>
</div>
</body>
</html>
