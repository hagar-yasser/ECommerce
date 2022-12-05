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
    <h1>Update Form</h1>
    <form:form modelAttribute="admin" method="post">
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
             <form:input path="password" cssClass="form-control" id="password"/>
             <form:errors path="password" cssClass="error"/>
        </div>
        <div class="from-row">
             <label for="isAdmin">Is Admin</label>
             <form:input path="isAdmin" cssClass="form-control" id="isAdmin"/>
             <form:errors path="isAdmin" cssClass="error"/>
        </div>

        <br>
        <input class="btn btn-primary" type="submit" value="Update Admin">
    </form:form>
    <a href="${pageContext.request.contextPath }/shopping/login/logout"><input class="btn btn-primary" type="submit" value="Logout" /></a>


</div>
</body>
</html>
