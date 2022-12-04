<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>

	<h3>Enter email for activation</h3>
	${message }
	<form method="post"
		action="${pageContext.request.contextPath }/shopping/login/login">
		<table border="0" cellpadding="2" cellspacing="2">
			<tr>
				<td>Email</td>
				<td><input type="email" name="email"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Send Email"></td>
			</tr>
		</table>
	</form>

</body>
</html>