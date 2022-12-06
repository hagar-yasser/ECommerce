<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%    request.setCharacterEncoding("UTF8");   %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html xmlns:spring="http://www.springframework.org/tags" xmlns:form="http://www.springframework.org/tags/form">

<head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <title>Error</title>
</head>
<body class="container">
<div class="card">
       <div class="card-body">
<h1 style="color:red">Error: ${message}</h1>
</div>
</div>
</body>
</html>