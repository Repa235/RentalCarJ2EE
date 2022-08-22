<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Antonio Repaci
  Date: 29/07/2022
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Utente u = (Utente) request.getAttribute("utente");
    request.setAttribute("utente",u);
%>
<html>
<head>
    <title>Prenota veicolo</title>
</head>
<body>
<%@include file="../header.jsp"%>
<h1>Prenota un'auto</h1>
<form action="PrenotaVeicolo" method="post">
    <input type="hidden" name="idUtente" value="${utente.id}">
    <br>
    Data inizio: <input name="dataInizio" type="date">
    <br>
    Data fine: <input name="dataFine" type="date">
    <br>
    Veicolo:
    <select name="veicolo">
    <c:forEach var="v" items="${requestScope.lv}">
        <option value="${v.id}">${v.casaCostruttrice} ${v.modello}</option>
    </c:forEach>
    </select>
    <br>
    <input type="submit" value="Chiedi prenotazione">
</form>

</body>
</html>
