<%--
  Created by IntelliJ IDEA.
  User: Antonio Repaci
  Date: 29/07/2022
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SUCCESSO</title>
</head>
<body>
<%@include file="header.jsp"%>
<h1>Si è verificato un errore</h1>
<h2> ${errore} </h2>
<a href="UtenteServlet?comando=profiloUtente">TORNA ALLA HOME</a>
</body>
</html>
