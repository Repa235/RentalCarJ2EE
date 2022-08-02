<%--
  Created by IntelliJ IDEA.
  User: Antonio Repaci
  Date: 29/07/2022
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Aggingi veicolo</title>
</head>
<body>
<%@include file="../header.jsp"%>
<h1>Aggiungi un'auto</h1>
<form action="AggiungiVeicolo" method="post">
    Anno di immatricolazione: <input name="annoImmatricolazione" type="number" min="1950">
    <br>
    Casa costruttrice: <input name="casaCostruttrice" type="text">
    <br>
    Modello: <input name="modello" type="text">
    <br>
    Tipo: <select name="tipo">
    <option value="minivan">minivan</option>
    <option value="furgone">furgone</option>
    <option value="suv">suv</option>
    <option value="berlina">berlina</option>
</select>
    <br>
    <input type="submit" value="Aggiungi">
</form>

</body>
</html>
