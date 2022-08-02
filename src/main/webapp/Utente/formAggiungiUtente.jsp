<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Aggiungi utente</title>
</head>
<body>
<%@include file="../header.jsp"%>
<h1><%= "Form registrazione utente" %>
</h1>
<br/>
<form action="UtenteServlet" method="post">
    <input type="hidden" name="comando" value="aggiungi">
    Nome: <input type="text" name="nome">
    <br>
    Cognome: <input type="text" name="cognome">
    <br>
    Data di nascita: <input type="date" name="dataNascita">
    <br>
    Username: <input type="text" name="username">
    <br>
    Password: <input type="password" name="password">
    <br>
    <input type="submit" name="Registra">
</form>
</body>
</html>