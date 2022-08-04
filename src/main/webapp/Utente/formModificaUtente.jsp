<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modifica utente</title>
</head>
<body>
<%@include file="../header.jsp"%>
<%@ page import="com.example.auto_park.hibernate.entity.Utente" %>
<h1><%= "Form modifica utente" %>
</h1>
<br/>
<form action="UtenteServlet" method="post">
    <input type="hidden" name="id" value="${utenteDaModificare.id}">
    <input type="hidden" name="comando" value="modifica">
    Nome: <input type="text" name="nome" value="${utenteDaModificare.nome}">
    <br>
    Cognome: <input type="text" name="cognome" value="${utenteDaModificare.cognome}">
    <br>
    Data di nascita: <input type="date" name="dataNascita" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${utenteDaModificare.dataNascita}" />">
    <br>
    Username: <input type="text" name="username" value="${utenteDaModificare.username}">
    <br>
    Password: <input type="password" name="password" value="${utenteDaModificare.password}">
    <br>
    <input type="submit" name="Modifica">
</form>
</body>
</html>