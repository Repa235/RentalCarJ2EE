<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Antonio Repaci
  Date: 29/07/2022
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>


<html>
<head>
    <title>Modifica prenotazione</title>
</head>
<body>
<%@include file="../header.jsp"%>
<h1> Modifica una prenotazione </h1>

<form action="PrenotazioneServlet" method="post" style="width: 700px">
    <div class="input-group flex-nowrap">
        <input type="hidden" name="comando" value="modificaAggiungiPrenotazione">
        <input type="hidden" name="idPrenotazione" value="${prenotazione.id}">
        <span class="input-group-text" id="addon-wrapping">Data inizio</span>
        <input class="form-control" name="dataInizio" type="date" value="${requestScope.prenotazione.dataInizio}">
        <span class="input-group-text">Data fine</span>
        <input class="form-control" name="dataFine" type="date" value="${requestScope.prenotazione.dataFine}">
    </div>
    <div class="input-group flex-nowrap" >
        <span class="input-group-text">Veicolo</span>
        <select class="form-control" name="veicolo" >
            <c:forEach var="v" items="${veicoli}" >
                <option value="${v.id}" selected="${prenotazione.veicolo.casaCostruttrice} ${prenotazione.veicolo.modello}">${v.casaCostruttrice} ${v.modello}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Modifica prenotazione">
    </div>
</form>

</body>
</html>
