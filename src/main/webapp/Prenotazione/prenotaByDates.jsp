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
<p>Veicoli disponibili dal ${dataInizio} al ${dataFine}</p>
<table class="table table-bordered">
    <tr>
        <th>Casa costruttrice</th>
        <th>Modello</th>
        <th>Prenota</th>
    </tr>
    <c:forEach var="ve" items="${veicoliDisponibili}">
        <tr>
            <td>${ve.casaCostruttrice}</td>
            <td>${ve.modello}</td>
            <td>
                <form action="PrenotazioneServlet" method="post">
                <input type="hidden" name="idUtente" value="${utente.id}">
                <input type="hidden" name="idVeicolo" value="${ve.id}">
                <input type="hidden" name="dataInizio" value="${dataInizio}">
                <input type="hidden" name="dataFine" value="${dataFine}">
                <input type="hidden" name="comando" value="aggiungiPrenotazione">
                <input type="submit" value="Prenota">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
