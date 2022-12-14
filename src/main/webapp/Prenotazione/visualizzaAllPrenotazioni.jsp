<%--
  Created by IntelliJ IDEA.
  User: Antonio Repaci
  Date: 29/07/2022
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <title>Visualizza prenotazioni</title>
</head>
<body>
<%@include file="../header.jsp" %>
<h2>Lista delle prenotazioni da gestire</h2>
<table class="table table-bordered">
    <tr>
        <th>Data inizio</th>
        <th>Data fine</th>
        <th>Richiedente</th>
        <th>Veicolo</th>
        <th>Approva</th>
    </tr>
    <c:forEach var="prenotazione" items="${listaprenotazioni}">
        <c:choose>
            <c:when test="${!prenotazione.approvato}">
        <tr>

            <td>${prenotazione.dataInizio}</td>
            <td>${prenotazione.dataFine}</td>
            <td>${prenotazione.utente.nome} ${prenotazione.utente.cognome}</td>
            <td>${prenotazione.veicolo.casaCostruttrice} ${prenotazione.veicolo.modello}</td>
            <td>
                <form method="post" action="PrenotazioneServlet">
                    <input type="hidden" name="comando" value="approvaPrenotazione">
                    <input type="hidden" name="idPrenotazione" value="${prenotazione.id}">
                    <select name="approva">
                        <option value="true">Si</option>
                        <option value="false">No</option>
                        <option value="delete">Elimina</option>
                    </select>
                    <input type="submit" value="vai">
                </form>
            </td>
        </tr>
            </c:when>
        </c:choose>
    </c:forEach>
</table>
<h2>Lista delle prenotazioni approvate</h2>
<table class="table table-bordered">
    <tr>
        <th>Data inizio</th>
        <th>Data fine</th>
        <th>Richiedente</th>
        <th>Veicolo</th>

    </tr>
    <c:forEach var="prenotazione" items="${listaprenotazioni}">
        <c:choose>
            <c:when test="${prenotazione.approvato}">
                <tr>

                    <td>${prenotazione.dataInizio}</td>
                    <td>${prenotazione.dataFine}</td>
                    <td>${prenotazione.utente.nome} ${prenotazione.utente.cognome}</td>
                    <td>${prenotazione.veicolo.casaCostruttrice} ${prenotazione.veicolo.modello}</td>
                </tr>
            </c:when>
        </c:choose>
    </c:forEach>
</table>
</body>
</html>
