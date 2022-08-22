<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modifica utente</title>
</head>
<body>
<%@include file="../header.jsp"%>
<h1><%= "Form modifica utente" %>
</h1>
<br/>
<form action="UtenteServlet" method="post">
    <input type="hidden" name="id" value="${utenteDaModificare.id}">
    <input type="hidden" name="comando" value="modifica">
    <div class="input-group flex-nowrap">
        <span class="input-group-text" id="addon-wrapping">Nome</span>
        <input  class="form-control"  type="text" name="nome" value="${utenteDaModificare.nome}">
        <span class="input-group-text" id="addon-wrapping">Cognome</span>
        <input  class="form-control"  type="text" name="cognome" value="${utenteDaModificare.cognome}">
        <span class="input-group-text" id="addon-wrapping">Data di nascita</span>
        <input class="form-control"  type="date" name="dataNascita" value="${utenteDaModificare.dataNascita}">
    </div>
    <div class="input-group flex-nowrap">
        <span class="input-group-text" id="addon-wrapping">Username</span>
        <input class="form-control" type="text" name="username" value="${utenteDaModificare.username}">
        <span class="input-group-text" id="addon-wrapping">Password</span>
        <input class="form-control" type="password" name="password" value="${utenteDaModificare.password}">
        <input type="submit" name="Modifica" value="Modifica">
    </div>

</form>
</body>
</html>