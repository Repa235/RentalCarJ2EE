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
<%@include file="../header.jsp" %>
<h1>Aggiungi un'auto</h1>
<form action="VeicoloServlet" method="post">
    <input type="hidden" name="comando" value="aggiungiVeicolo">
    <div class="input-group flex-nowrap">
        <span class="input-group-text" id="addon-wrapping">Anno di immatricolazione</span>
        <input class="form-control" name="annoImmatricolazione" type="number" min="1950">
        <span class="input-group-text" id="addon-wrapping">Casa costruttrice</span>
        <input class="form-control" name="casaCostruttrice" type="text">
    </div>
    <div class="input-group flex-nowrap">
        <span class="input-group-text" id="addon-wrapping">Modello</span>
        <input class="form-control" name="modello" type="text">
        <span class="input-group-text" id="addon-wrapping">Tipo</span>
        <select class="form-control" name="tipo">
            <option value="minivan">minivan</option>
            <option value="furgone">furgone</option>
            <option value="suv">suv</option>
            <option value="berlina">berlina</option>
        </select>
        <br>
        <input type="submit" value="Aggiungi">
    </div>
</form>

</body>
</html>
