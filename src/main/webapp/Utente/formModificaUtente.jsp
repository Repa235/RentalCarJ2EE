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
<%
Utente utente = (Utente) request.getAttribute("utente");
request.setAttribute("utente",utente);
%>
<h1><%= "Form modifica utente" %>
</h1>
<br/>
<form action="ModificaUtente" method="post">
    <input type="hidden" name="id" value="${utente.id}">
    Nome: <input type="text" name="nome" value="${utente.nome}">
    <br>
    Cognome: <input type="text" name="cognome" value="${utente.cognome}">
    <br>
    Data di nascita: <input type="date" name="dataNascita" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${utente.dataNascita}" />">
    <br>
    Username: <input type="text" name="username" value="${utente.username}">
    <br>
    Password: <input type="password" name="password" value="${utente.password}">
    <br>
    <input type="submit" name="Registra">
</form>
</body>
</html>