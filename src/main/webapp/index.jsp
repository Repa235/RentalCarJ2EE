<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Form registrazione utente" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<form action="InserisciUtente" method="post">
    Nome: <input type="text" name="nome">
    <br>
    Cognome: <input type="text" name="cognome">
    <br>
    Data di nascita: <input type="date" name="data_nascita">
    <br>
    Tipo: <input type="text" name="tipo">
    <br>
    Username: <input type="text" name="username">
    <br>
    Password: <input type="password" name="password">
    <br>
    <input type="submit" name="Registra">
</form>
</body>
</html>