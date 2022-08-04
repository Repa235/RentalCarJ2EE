<%--
  Created by IntelliJ IDEA.
  User: Antonio Repaci
  Date: 04/08/2022
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Prenota by data</title>
</head>
<body>
<%@include file="../header.jsp"%>
<h1> Prenota un veicolo </h1>
<p>Seleziona il range che ti interessa</p>
<form action="PrenotazioneServlet" method="post">
    <input type="hidden" name="comando" value="visualizzaVeicoliDisponibili">
    Data inizio: <input name="dataInizio" type="date">
    <br>
    Data fine: <input name="dataFine" type="date">
    <br>
    <input type="submit" value="Cerca veicoli">
</form>

</body>
</html>
