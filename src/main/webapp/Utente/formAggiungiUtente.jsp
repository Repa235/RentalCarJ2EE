<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Aggiungi utente</title>
</head>
<body>
<%@include file="../header.jsp" %>
<h1><%= "Form registrazione utente" %>
</h1>
<br/>
<form action="UtenteServlet" method="post" style="width: 700px">
    <div class="input-group flex-nowrap">
        <input type="hidden" name="comando" value="modificaAggiungi">
        <span class="input-group-text" id="addon-wrapping">Nome</span>
        <input  class="form-control"  type="text" name="nome">
        <span class="input-group-text" id="addon-wrapping">Cogome</span>
        <input  class="form-control"  type="text" name="cognome">
        <span class="input-group-text" id="addon-wrapping">Data di nascita</span>
        <input class="form-control"  type="date" name="dataNascita">
    </div>
    <div class="input-group flex-nowrap">
        <span class="input-group-text" id="addon-wrapping">Username</span>
        <input class="form-control" type="text" name="username">
        <span class="input-group-text" id="addon-wrapping">Password</span>
        <input class="form-control" type="password" name="password">
        <input  type="submit" name="Registra" value="Registra">
    </div>
</form>
</body>
</html>