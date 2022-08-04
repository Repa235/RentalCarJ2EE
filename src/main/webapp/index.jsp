<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Homepage</title>
</head>
<body>
<%@include file="header.jsp" %>
<h1>Benvenuto nel parco auto SI2001</h1>
<c:if test="${requestScope.showForm}">
<form action="UtenteServlet" method="post">
    <input type="hidden" name="comando" value="login">
    Username: <input type="text" name="username">
    Password: <input type="password" name="password">
    <input type="submit" value="Login">
</form>
</c:if>
</body>
</html>