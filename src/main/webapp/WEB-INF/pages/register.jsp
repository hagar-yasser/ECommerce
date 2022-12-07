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
<h1>Registration</h1>
<div class="card">
       <div class="card-body">
    <form:form modelAttribute="customer" method="post">
        <div class="from-row md-4">
            <label for="firstName">First Name</label>
            <form:input path="firstName" cssClass="form-control" id="firstName"/>
            <form:errors path="firstName" cssClass="error"/>
        </div>
        <div class="from-row">
            <label for="lastName">Last Name</label>
            <form:input path="lastName" cssClass="form-control" id="lastName"/>
            <form:errors path="lastName" cssClass="error"/>
        </div>
        <div class="from-row">
             <label for="email">Email</label>
             <form:input path="email" cssClass="form-control" id="email"/>
             <form:errors path="email" cssClass="error"/>
        </div>
        <div class="from-row">
             <label for="password">PassWord</label>
             <form:input path="password" type= "password" cssClass="form-control" id="password"/>
             <form:errors path="password" cssClass="error"/>
        </div>

        <br>
        <input type="submit" class="btn btn-primary"  value="Register">
    </form:form>
</div>
</div>
</div>
</div>
</body>
</html>
