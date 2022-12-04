<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Items</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body class="container">
    <h1>Items</h1>


 <div class="card">
       <div class="card-body">
<table class="table">
    <thead class="table table-dark">
    <tr>
        <td>ID</td>
        <td>NAME</td>
        <td>CATEGORY</td>
        <td>PRICE</td>
        <td>RATING</td>
        <td>IMAGE</td>
<<<<<<< HEAD
        <td>QUANTITY</td>
=======
>>>>>>> 1886224aef4bbc91fc8de307c375c27c831ac724
        <td>ACTION</td>



    </tr>
    </thead>
    <tbody>

    <c:if test="${!empty itemsList}">
        <c:forEach var="item" items="${itemsList}">
            <form method="post" action="${pageContext.request.contextPath }/shopping/Cart/addItem/${item.itemId}">
            <tr>
                <td>${item.itemId}</td>
                <td>${item.name}</td>
                <td>${item.category}</td>
                <td>${item.price}</td>
                <td>${item.rating}</td>
<<<<<<< HEAD
=======
                <td>${item.quantity}</td>


>>>>>>> 1886224aef4bbc91fc8de307c375c27c831ac724
                <td></td>
                <c:if test="${item.image!=null}">
                    <td><img src=${item.imageUrlForJSP} alt="wrong Image" width="100 "height="100"/></td>
                </c:if>

<%--                <td> <input type="text" id="quantity" name ="quantity"> </td>--%>

                <td><input class="form-control" type="number" name="quantity"/></td>



<<<<<<< HEAD
                <td><input class="btn btn-primary" type="submit" value="add to cart" /></td>
=======
>>>>>>> 1886224aef4bbc91fc8de307c375c27c831ac724
                <c:if test="${item.image!=null}">
                    <td>${item.image}</td>
                </c:if>
            </tr>
            </form>
        </c:forEach>

    </c:if>
    </tbody>

</table>
</div>
</div>


</body>
</html>
