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
<%@include file="../header.jsp" %>
<h1> Prenota un veicolo </h1>
<p>Seleziona il range che ti interessa</p>
<form action="PrenotazioneServlet" method="post">
    <div class="input-group flex-nowrap">
        <input type="hidden" name="comando" value="visualizzaVeicoliDisponibili">
        <span class="input-group-text" id="addon-wrapping">Data inizio</span>
        <input class="form-control" name="dataInizio" type="date">
        <span class="input-group-text">Data fine</span>
        <input class="form-control" name="dataFine" type="date">
        <input type="submit" value="Cerca veicoli">
    </div>
</form>

</body>
</html>